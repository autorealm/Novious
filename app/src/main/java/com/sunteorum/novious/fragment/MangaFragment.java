package com.sunteorum.novious.fragment;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.sunteorum.novious.R;
import com.sunteorum.novious.widget.GestureImageView;

/**
 * Created by KYO on 2015/8/9.
 */
public class MangaFragment extends Fragment {
	public static final String TAG = MangaFragment.class.getSimpleName();

	public static final String URL_ARGUMENT_KEY = TAG + ":" + "UrlArgumentKey";
	public static final String POSITION_ARGUMENT_KEY = TAG + ":" + "PositionArgumentKey";

	private GestureImageView mGestureImageView;

	private String mUrl;
	private int mPosition;

	public static MangaFragment newInstance(String url, int position) {
		MangaFragment newInstance = new MangaFragment();

		Bundle arguments = new Bundle();
		arguments.putString(URL_ARGUMENT_KEY, url);
		arguments.putInt(POSITION_ARGUMENT_KEY, position);
		newInstance.setArguments(arguments);

		return newInstance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle arguments = getArguments();
		if (arguments != null) {
			if (arguments.containsKey(URL_ARGUMENT_KEY)) {
				mUrl = arguments.getString(URL_ARGUMENT_KEY);
			}
			if (arguments.containsKey(POSITION_ARGUMENT_KEY)) {
				mPosition = arguments.getInt(POSITION_ARGUMENT_KEY);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View pageView = inflater.inflate(R.layout.fragment_manga, container, false);

		mGestureImageView = (GestureImageView) pageView.findViewById(R.id.image);

		return pageView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mGestureImageView.setScaleType(ImageView.ScaleType.CENTER);

		Drawable placeHolderDrawable = getResources().getDrawable(R.mipmap.ic_image_white_48dp);
		placeHolderDrawable.setColorFilter(getResources().getColor(R.color.accent_pink_a200), PorterDuff.Mode.MULTIPLY);
		Drawable errorHolderDrawable = getResources().getDrawable(R.mipmap.ic_error_white_48dp);
		errorHolderDrawable.setColorFilter(getResources().getColor(R.color.accent_pink_a200), PorterDuff.Mode.MULTIPLY);

		GenericDraweeHierarchyBuilder builder =
				new GenericDraweeHierarchyBuilder(getResources());
		GenericDraweeHierarchy hierarchy = builder
				.setFadeDuration(300)
				.setPlaceholderImage(placeHolderDrawable) //占位图
				.setFailureImage(errorHolderDrawable) //设置加载失败占位图
				.setBackgrounds(null) //背景
				.setOverlays(null) //设置叠加图
				.setPressedStateOverlay(null) //设置按压状态下的叠加图
				//.setActualImageColorFilter(null)
				.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
				//.setActualImageFocusPoint(null)
				.build();

		//RoundingParams roundingParams = hierarchy.getRoundingParams();
		//roundingParams.setCornersRadius(10);
		//hierarchy.setRoundingParams(roundingParams);

		ControllerListener listener = new BaseControllerListener() {

			@Override
			public void onSubmit(String id, Object callerContext) {
				super.onSubmit(id, callerContext);
				mGestureImageView.initializeView();
			}
		};

		//后处理器：对获取的图片做些修改
		Postprocessor myPostprocessor = new Postprocessor() {

			@Override
			public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
				return null;
			}

			@Override
			public String getName() {
				return null;
			}

			@Override
			public CacheKey getPostprocessorCacheKey() {
				return null;
			}
		};

		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(mUrl))
				.setPostprocessor(myPostprocessor)
				.setAutoRotateEnabled(true) //自动旋转
				.setProgressiveRenderingEnabled(true) //允许渐进式JPEG图片加载
				.build();

		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setImageRequest(request)
				.setTapToRetryEnabled(true) //点击重新加载图
				.setOldController(mGestureImageView.getController())
				.setControllerListener(listener)
				.build();

		mGestureImageView.setHierarchy(hierarchy);
		mGestureImageView.setController(controller);
		//mGestureImageView.setImageURI(uri);

	}

}
