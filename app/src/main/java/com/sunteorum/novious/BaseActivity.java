package com.sunteorum.novious;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sunteorum.novious.util.ExpertApi;
import com.sunteorum.novious.util.RequestBean;
import com.sunteorum.novious.util.RetrofitUtils;

import retrofit.client.Response;

public class BaseActivity extends Activity implements View.OnClickListener {

	private ExpertApi mExpertApi;

	//...


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ...

		mExpertApi = RetrofitUtils.createApi(this, ExpertApi.class);
	}

	@Override
	public void onClick(View v) {
		//...调用rest 接口
		mExpertApi.ask("getToken()", "mUserInfo.getId()", "title", "question", "mExpertId",
				new AskCallback(BaseActivity.this));
	}

	//回调接口返回从服务器返回的数据
	private static final class AskCallback extends RetrofitUtils.ActivityCallback<RequestBean> {


		public AskCallback(BaseActivity activity) {
			super(activity);
		}


		@Override
		public void success(RequestBean requestbean, Response response) {
			BaseActivity activity = (BaseActivity) getActivity();


			if (requestbean.succeed()) {
				Toast.makeText(activity, "成功返回数据", Toast.LENGTH_SHORT).show();
				activity.finish();
			} else {
				Toast.makeText(activity, response.getReason(), Toast.LENGTH_SHORT).show();
			}
		}

	}
}