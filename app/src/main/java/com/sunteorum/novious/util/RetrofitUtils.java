package com.sunteorum.novious.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class RetrofitUtils {

	private static RestAdapter singleton;

	/**
	 * 创建API实例
	 *
	 * @param clazz Api定义类的类型
	 * @param <T> 范型
	 * @return API实例
	 */
	public static <T> T createApi(Context context, Class<T> clazz) {
		if (singleton == null) {
			synchronized (RetrofitUtils.class) {
				if (singleton == null) {
					RestAdapter.Builder builder = new RestAdapter.Builder();
					builder.setEndpoint("");//设置远程地址
					builder.setConverter(new GsonConverter(GsonUtils.newInstance()));
					builder.setClient(new OkClient(OkHttpUtils.getInstance(context)));
					builder.setLogLevel(
							Config.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);
					singleton = builder.build();
				}
			}
		}
		return singleton.create(clazz);
	}

	public static abstract class ActivityCallback<T> implements Callback<T> {
		//软引用，缓存引用对象
		private final WeakReference<Activity> mRef;


		public ActivityCallback(Activity activity) {
			mRef = new WeakReference<Activity>(activity);
		}


		public Activity getActivity() {
			return mRef.get();
		}

		@Override
		public void failure(RetrofitError error) {
			final Activity activity = mRef.get();

			Response response = error.getResponse();
			if (response != null) {
				Toast.makeText(activity, activity.getString(android.R.string.httpErrorUnsupportedScheme),
						Toast.LENGTH_SHORT).show();
				System.out.println("code:" + response.getStatus() + ", reason:" + response.getReason());
				error.printStackTrace();
			}

		}


	}
}
