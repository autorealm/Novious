package com.sunteorum.novious;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.sunteorum.novious.helper.DatabaseHelper;
import com.sunteorum.novious.model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Novious Application
 * Created by KYO on 2015/8/8.
 */
public class Novious extends Application {
	final static String CONF = "novious_config";	//程序配置保存名
	public final static String APP_NAME = "Novious";
	final String APP_DIR = "Sunteorum" + File.separator + APP_NAME;
	final String APP_CACHE_DIR = "Cache";
	final String APP_IMAGE_DIR = "Image";

	protected volatile boolean DEBUG = true;
	private static Novious sInstance;
	private static Context context;

	private static Bus BusProvider;

	private DatabaseHelper DbHelper;
	private User user;
	private BroadcastReceiver mReceiver = null;

	/** 异步任务列表  */
	protected List<AsyncTask<?, ?, ?>> mAsyncTasks;

	/** <Novious>的应用配置 */
	private static SharedPreferences prefs;

	@Override
	public void onCreate() {
		super.onCreate();

		StrictMode.enableDefaults();
		sInstance = this;
		context = getApplicationContext();

		if (android.os.Build.VERSION.SDK_INT > 10)
			prefs = getSharedPreferences(CONF, Context.MODE_MULTI_PROCESS);
		else
			prefs = getSharedPreferences(CONF, Context.MODE_PRIVATE);

		mAsyncTasks = new ArrayList<>();

		//CrashHandler.getInstance().init(getApplicationContext());

		//初始化 Fresco.
		Fresco.initialize(getApplicationContext());

		BusProvider = new Bus(ThreadEnforcer.MAIN);
		BusProvider.register(this);

		initialize();
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();

		if (DbHelper != null) DbHelper.close();
		if (mReceiver != null) unregisterReceiver(mReceiver);
		this.saveConfig("last_exit_time", System.currentTimeMillis());

		Fresco.shutDown();

		BusProvider.unregister(this);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * 初始化应用配置
	 */
	protected void initialize() {
		SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
		dsp.contains(null);

	}

	/** 得到应用实例 */
	public static synchronized Novious getInstance() {
		return sInstance;
	}

	public static Context getAppContext() {
		return Novious.context;
	}

	/** 默认的配置引用 */
	public SharedPreferences getPreferences() {
		return prefs;
	}

	/** 获取数据库Helper */
	public DatabaseHelper getDatabaseHelper() {
		if (DbHelper == null)
			DbHelper = DatabaseHelper.getInstance(context);
		return DbHelper;
	}

	/** 用户实例  */
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/* ----------------------------------------- Dirs -------------------------------------------*/

	/**
	 * 获取应用的默认缓存目录
	 * @return
	 */
	public File getAppDir() {
		File appDir;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			appDir = new File(Environment.getExternalStorageDirectory(), APP_DIR);
		else
			return this.getDir(APP_DIR, Context.MODE_PRIVATE);
		if (!appDir.exists()) appDir.mkdirs();

		return appDir;
	}

	public File getAppCacheDir() {
		File cacheDir;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			cacheDir = new File(Environment.getExternalStorageDirectory(), APP_DIR + File.separator + APP_CACHE_DIR);
		else
			return this.getCacheDir();
		if (!cacheDir.exists()) cacheDir.mkdirs();

		return cacheDir;
	}

	public File getAppImageDir() {
		File imageDir;
		File appDir = getAppDir();
		if (appDir == null || !appDir.exists()) return null;
		imageDir = new File(appDir, APP_IMAGE_DIR);
		if (!imageDir.exists()) imageDir.mkdirs();

		return imageDir;
	}

	/**
	 * 获取图片URL的本地保存路径，若本地文件不存在将返回NULL。
	 * @param imageUrl
	 * @return
	 */
	public String getCacheImagePath(String imageUrl) {
		File f = null;

		if (imageUrl.toLowerCase(Locale.getDefault()).startsWith("file://")) {
			f = new File(Uri.parse(imageUrl).getPath());
			if (f.exists()) return f.getAbsolutePath();
		}

		f = new File(this.getAppCacheDir(), this.getCacheImageName(imageUrl));
		String imagePath = f.getAbsolutePath();
		if (!f.exists()) {
			return null;
		}

		return imagePath;
	}

	/**
	 * 获取 URL 地址的缓存文件名称
	 * @param imageUrl
	 * @return
	 */
	public String getCacheImageName(String imageUrl) {
		String name = String.valueOf(imageUrl.hashCode());

		return name;
	}

	/* -------------------------------------- AsyncTask -----------------------------------------*/

	/**
	 * 添加并执行异步任务
	 * @param asyncTask
	 */
	protected void putAsyncTask(AsyncTask<Object, Object, Object> asyncTask) {
		mAsyncTasks.add(asyncTask.execute());

	}

	/**
	 * 清空并停止全部异步任务
	 */
	protected void clearAsyncTask() {
		Iterator<AsyncTask<?, ?, ?>> iterator = mAsyncTasks.iterator();
		while (iterator.hasNext()) {
			AsyncTask<?, ?, ?> asyncTask = iterator.next();
			if (asyncTask != null && !asyncTask.isCancelled()) {
				asyncTask.cancel(true);
			}
		}

		mAsyncTasks.clear();
	}

	public static <Params, Progress, Result> void executeAsyncTask(
			AsyncTask<Params, Progress, Result> task, Params... params) {
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
		} else {
			task.execute(params);
		}
	}

	/* ---------------------------------------- Timer -------------------------------------------*/

	/** 定时任务 */
	private volatile Timer myTimer;
	private final HashMap<Runnable,Long> myTimerTaskPeriods = new HashMap<Runnable,Long>();
	private final HashMap<Runnable,TimerTask> myTimerTasks = new HashMap<Runnable,TimerTask>();

	private static class MyTimerTask extends TimerTask {
		private final Runnable myRunnable;

		MyTimerTask(Runnable runnable) {
			myRunnable = runnable;
		}

		@Override
		public void run() {
			myRunnable.run();
		}
	}

	private void addTimerTaskInternal(Runnable runnable, long periodMilliseconds) {
		final TimerTask task = new MyTimerTask(runnable);
		myTimer.schedule(task, periodMilliseconds / 2, periodMilliseconds);
		myTimerTasks.put(runnable, task);
	}

	private final Object myTimerLock = new Object();
	public final void startTimer() {
		synchronized (myTimerLock) {
			if (myTimer == null) {
				myTimer = new Timer();
				for (Map.Entry<Runnable,Long> entry : myTimerTaskPeriods.entrySet()) {
					addTimerTaskInternal(entry.getKey(), entry.getValue());
				}
			}
		}
	}

	public final void stopTimer() {
		synchronized (myTimerLock) {
			if (myTimer != null) {
				myTimer.cancel();
				myTimer = null;
				myTimerTasks.clear();
			}
		}
	}

	public final void addTimerTask(Runnable runnable, long periodMilliseconds) {
		synchronized (myTimerLock) {
			removeTimerTask(runnable);
			myTimerTaskPeriods.put(runnable, periodMilliseconds);
			if (myTimer != null) {
				addTimerTaskInternal(runnable, periodMilliseconds);
			}
		}
	}

	public final void removeTimerTask(Runnable runnable) {
		synchronized (myTimerLock) {
			TimerTask task = myTimerTasks.get(runnable);
			if (task != null) {
				task.cancel();
				myTimerTasks.remove(runnable);
			}
			myTimerTaskPeriods.remove(runnable);
		}
	}

	/* ---------------------------------- SharedPreferences -------------------------------------*/

	/**
	 * 保存应用配置
	 * @param conf
	 * @return
	 */
	public boolean saveConfig(Map<String, Object> conf) {
		SharedPreferences.Editor edt = prefs.edit();

		for (String k : conf.keySet()) {
			Object v = conf.get(k);

			if (String.class.isInstance(v)) {
				edt.putString(k, v.toString());

			} else if (Integer.class.isInstance(v)) {
				edt.putInt(k, Integer.parseInt(v.toString()));

			} else if (Long.class.isInstance(v)) {
				edt.putLong(k, Long.parseLong(v.toString()));

			} else if (Boolean.class.isInstance(v)) {
				edt.putBoolean(k, Boolean.parseBoolean(v.toString()));

			} else if (Float.class.isInstance(v)) {
				edt.putFloat(k, Float.parseFloat(v.toString()));

			} else {
				edt.putString(k, v.toString());
			}

		}

		return edt.commit();
	}

	protected boolean saveConfig(String k, Object v) {
		SharedPreferences.Editor edt = prefs.edit();

		if (String.class.isInstance(v)) {
			edt.putString(k, v.toString());

		} else if (Integer.class.isInstance(v)) {
			edt.putInt(k, Integer.parseInt(v.toString()));

		} else if (Long.class.isInstance(v)) {
			edt.putLong(k, Long.parseLong(v.toString()));

		} else if (Boolean.class.isInstance(v)) {
			edt.putBoolean(k, Boolean.parseBoolean(v.toString()));

		} else if (Float.class.isInstance(v)) {
			edt.putFloat(k, Float.parseFloat(v.toString()));

		} else {
			edt.putString(k, v.toString());
		}

		return edt.commit();
	}

	protected boolean removeConfig(String key) {
		SharedPreferences.Editor edt = prefs.edit();
		if (prefs.contains(key)) {
			edt.remove(key);
			return edt.commit();
		}

		return true;
	}

	protected boolean containsConfig(String key) {
		return prefs.contains(key);

	}

	public String getConfig(String key) {
		if (prefs.contains(key)) return prefs.getString(key, null);
		else return null;

	}

	protected String getConfigString(String key, String default_value) {
		return prefs.getString(key, default_value);

	}

	protected int getConfigInt(String key, int default_value) {
		return prefs.getInt(key, default_value);

	}

	protected boolean getConfigBoolean(String key, boolean default_value) {
		return prefs.getBoolean(key, default_value);

	}

	protected long getConfigLong(String key, long default_value) {
		return prefs.getLong(key, default_value);

	}

	protected float getConfigFloat(String key, float default_value) {
		return prefs.getFloat(key, default_value);

	}

	/**
	 * 获取全部配置信息
	 * @param conf_name
	 * @return
	 */
	public static Map<String, Object> getConfigMap(String conf_name) {
		String cname = conf_name;
		if (conf_name == null || conf_name.trim().length() == 0) cname = CONF;

		SharedPreferences sdf = Novious.getAppContext().getSharedPreferences(cname, Context.MODE_PRIVATE);
		Map<String, Object> conf = new HashMap<String, Object>();

		List<Map.Entry<String, ?>> lme = new ArrayList<Map.Entry<String, ?>>(sdf.getAll().entrySet());
		for (int i = 0; i< lme.size(); i++) {
			try {
				conf.put(lme.get(i).getKey(), lme.get(i).getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return conf;
	}

}
