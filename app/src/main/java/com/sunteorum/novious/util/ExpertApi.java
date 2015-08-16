package com.sunteorum.novious.util;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;

public interface ExpertApi {

	@FormUrlEncoded
	@POST("/ellassy/teacher/ask")
	void ask(@Header("token") String token, @Field("member_id") String memberId,
			 @Field("title") String title, @Field("description") String description,
			 @Field("teacher_id") String expertId,
			 Callback<RequestBean> cb);
}
