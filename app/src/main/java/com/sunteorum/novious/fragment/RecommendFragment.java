package com.sunteorum.novious.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sunteorum.novious.R;
import com.sunteorum.novious.adapter.RecyclerViewAdapter;

/**
 * Created by KYO on 2015/8/9.
 */
public class RecommendFragment extends Fragment {
	private RecyclerView mListView;
	private RelativeLayout mEmptyRelativeLayout;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private FloatingActionButton fab;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_index_list, container, false);

		mListView = (RecyclerView) rootView.findViewById(R.id.list);
		mEmptyRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.emptyRelativeLayout);

		mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);

		mSwipeRefreshLayout.setProgressViewEndTarget(false, 0);
		mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
		mSwipeRefreshLayout.setColorSchemeColors(
				android.R.color.holo_blue_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_green_light,
				android.R.color.holo_red_light);

		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {

				doSwapeRefresh();
			}
		});

		mListView.setHasFixedSize(true);

		LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
		mListView.setLayoutManager(mLayoutManager);
		mListView.setAdapter(new RecyclerViewAdapter(getActivity(), "计数法", "四川", "形成佛牌",
				"先吃饭vkli", "西餐vji", "形成哦vidf"));

		mListView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener(){

			@Override
			public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
				return false;
			}

			@Override
			public void onTouchEvent(RecyclerView rv, MotionEvent e) {

			}

			@Override
			public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

			}
		});

		fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
		fab.setColorFilter(getActivity().getResources().getColor(R.color.accent_pink_a200));
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(getView(), "Click Ta...", Snackbar.LENGTH_LONG)
						.setAction(android.R.string.ok, null).show();
			}
		});

		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {

		}

	}

	@Override
	public void onStart() {
		super.onStart();

	}

	@Override
	public void onStop() {
		super.onStop();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}

	private void doSwapeRefresh() {
		mListView.getAdapter().notifyDataSetChanged();
		mListView.postDelayed(new Runnable() {

			@Override
			public void run() {
				mSwipeRefreshLayout.setRefreshing(false);
			}

		}, 1200);

	}

}
