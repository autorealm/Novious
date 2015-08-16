package com.sunteorum.novious;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.sunteorum.novious.adapter.FragmentAdapter;
import com.sunteorum.novious.fragment.CategoryFragment;
import com.sunteorum.novious.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private Toolbar mToolbar;
	private NavigationView mNavigationView;
	private DrawerLayout mDrawerLayout;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String title = getIntent().getStringExtra("title");

		// Set up the action bar to show a dropdown list.
		//final ActionBar actionBar = getSupportActionBar();
		//actionBar.setDisplayShowTitleEnabled(false);

		initView();

	}

	private void initView() {
		//MainActivity的布局文件中的主要控件初始化
		mToolbar = (Toolbar) this.findViewById(R.id.tool_bar);
		mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
		mNavigationView = (NavigationView) this.findViewById(R.id.navigation_view);
		mTabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
		mViewPager = (ViewPager) this.findViewById(R.id.view_pager);

		//初始化ToolBar
		setSupportActionBar(mToolbar);
		mToolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
		actionBar.setDisplayHomeAsUpEnabled(true);

		//对NavigationView添加item的监听事件
		mNavigationView.setNavigationItemSelectedListener(naviListener);
		//开启应用默认打开DrawerLayout
		//mDrawerLayout.openDrawer(mNavigationView);

		//初始化TabLayout的title数据集
		List<String> titles = new ArrayList<>();
		titles.add("推荐");
		titles.add("分区");
		//初始化TabLayout的title
		mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
		mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
		//初始化ViewPager的数据集
		List<Fragment> fragments = new ArrayList<>();
		fragments.add(new RecommendFragment());
		fragments.add(new CategoryFragment());

		//创建ViewPager的adapter
		FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
		mViewPager.setAdapter(adapter);

		mTabLayout.setupWithViewPager(mViewPager);
		mTabLayout.setTabsFromPagerAdapter(adapter);

	}

	private NavigationView.OnNavigationItemSelectedListener naviListener = new NavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(MenuItem menuItem) {
			//点击NavigationView中定义的menu item时触发反应
			switch (menuItem.getItemId()) {
				case R.id.menu_info_details:
					mViewPager.setCurrentItem(0);
					break;
				case R.id.menu_share:
					mViewPager.setCurrentItem(1);
					break;
				case R.id.menu_agenda:
					mViewPager.setCurrentItem(2);
					break;
			}
			menuItem.setChecked(true);
			//mDrawerLayout.closeDrawers();
			//关闭DrawerLayout回到主界面选中的tab的fragment页
			mDrawerLayout.closeDrawer(mNavigationView);
			return false;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		final SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String newText) {
				InputMethodManager searchKeyboard = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				searchKeyboard.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

				return true;
			}

			@Override
			public boolean onQueryTextChange(String query) {

				return true;
			}
		});
		//return super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				break;
			case android.R.id.home:

				mDrawerLayout.openDrawer(GravityCompat.START);
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	@Override
	protected void onResume() {
		super.onResume();
		//BusProvider.getBusInstance().register(this);
	}
}
