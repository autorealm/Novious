package com.sunteorum.novious;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by KYO on 2015/8/8.
 */
public class DetailsActivity extends AppCompatActivity {
	SimpleDraweeView mSimpleDraweeView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		Toolbar toolbar = (Toolbar) this.findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeAsUpIndicator(android.R.drawable.ic_input_delete);
		actionBar.setDisplayHomeAsUpEnabled(true);

		CollapsingToolbarLayout collapsingToolbar =
				(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		collapsingToolbar.setTitle("详情界面");

		mSimpleDraweeView = (SimpleDraweeView) this.findViewById(R.id.back_drop);

		//显示一张HTTP图片保持一定宽高比例，如果4:3(1.33f)，注意xml里的写法
		mSimpleDraweeView.setImageURI(Uri.parse(""));
		mSimpleDraweeView.setAspectRatio(1.33f);

		//显示一张HTTP的GIF图片
		DraweeController draweeController = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(""))
				.setAutoPlayAnimations(true).build();
		mSimpleDraweeView.setController(draweeController);

	}

}
