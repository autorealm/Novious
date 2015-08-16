package com.sunteorum.novious.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.sunteorum.novious.R;

/**
 * Created by KYO on 2015/8/9.
 */
public class CategoryFragment extends Fragment {
	private GridView mGridView;
	private RelativeLayout mEmptyRelativeLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View catalogueView = inflater.inflate(R.layout.fragment_index_category, container, false);

		mGridView = (GridView) catalogueView.findViewById(R.id.gridView);
		mEmptyRelativeLayout = (RelativeLayout) catalogueView.findViewById(R.id.emptyRelativeLayout);

		return catalogueView;
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


}
