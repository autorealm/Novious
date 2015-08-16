package com.sunteorum.novious.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunteorum.novious.R;

/**
 * Created by KYO on 2015/8/8.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
	private Context mContext;
	private String[] texts;

	public RecyclerViewAdapter(Context mContext, String... texts) {
		this.mContext = mContext;
		this.texts = texts;
	}

	@Override
	public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_text, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
		//holder.mTextView.setBackgroundColor(mContext.getResources().getColor(colors[position%(colors.length)]));
		holder.mTextView.setText(texts[position]);

		holder.mTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//mContext.startActivity(new Intent(mContext, SubActivity.class));
			}
		});
	}

	@Override
	public int getItemCount() {
		return texts.length;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public final TextView mTextView;

		public ViewHolder(View view) {
			super(view);
			mTextView = (TextView) view.findViewById(R.id.text);
		}
	}
}