package com.sunteorum.novious.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunteorum.novious.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class ForgeFragment extends Fragment {

	public ForgeFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_forge, container, false);
	}
}
