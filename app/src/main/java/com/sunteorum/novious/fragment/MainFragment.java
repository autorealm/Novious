package com.sunteorum.novious.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunteorum.novious.R;
import com.sunteorum.novious.adapter.RecyclerViewAdapter;

/**
 * Created by KYO on 2015/8/8.
 */
public class MainFragment extends Fragment {
	private RecyclerView mRecyclerView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recycler, container, false);
		return mRecyclerView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		LinearLayoutManager manager = new LinearLayoutManager(mRecyclerView.getContext());
		manager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity()));
	}

}
