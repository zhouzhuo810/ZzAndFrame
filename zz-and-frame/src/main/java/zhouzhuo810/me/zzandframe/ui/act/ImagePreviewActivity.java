package zhouzhuo810.me.zzandframe.ui.act;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;

import zhouzhuo810.me.zzandframe.R;
import zhouzhuo810.me.zzandframe.common.cons.ZzConst;

/**
 * 单张图片预览
 * Created by zz on 2015/12/29.
 */
public class ImagePreviewActivity extends BaseActivity {

    private PhotoView iv;
    private boolean defaultBack = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_img_preview;
    }

    @Override
    public void initView() {
        iv = (PhotoView) findViewById(R.id.act_img_preview_iv);
        iv.setMaximumScale(10.0f);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 21) {
                    onBackPressed();
                } else {
                    closeAct();
                }
            }
        });
    }

    @Override
    public void initData() {

        final String url = getIntent().getStringExtra(ZzConst.IMG_PRE_PIC_URL);
        defaultBack = getIntent().getBooleanExtra(ZzConst.IMG_PRE_DEFAULT_BACK, true);
        final int errorPicId = getIntent().getIntExtra(ZzConst.IMG_PRE_ERROR_PIC, -1);
        final boolean crossFade = getIntent().getBooleanExtra(ZzConst.IMG_PRE_CROSS_FADE, false);

        if (url != null) {
            if (url.startsWith("http")) {
                DrawableRequestBuilder<String> req = Glide.with(this)
                        .load(url);
                if (crossFade) {
                    req.crossFade();
                }
                req.listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        iv.getAttacher().update();
                        return false;
                    }
                }).into(iv);
            } else {
                DrawableRequestBuilder<File> req = Glide.with(this)
                        .load(new File(url));
                if (crossFade) {
                    req.crossFade();
                }
                req.listener(new RequestListener<File, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, File model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, File model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        iv.getAttacher().update();
                        return false;
                    }
                }).into(iv);
            }
        } else {
            iv.setImageResource(errorPicId == -1 ? R.drawable.ic_default : errorPicId);
        }
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void saveState(Bundle bundle) {

    }

    @Override
    public void restoreState(Bundle bundle) {

    }

    @Override
    public boolean defaultBack() {
        return defaultBack;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
