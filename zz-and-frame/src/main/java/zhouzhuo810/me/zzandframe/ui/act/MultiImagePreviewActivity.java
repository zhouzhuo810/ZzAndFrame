package zhouzhuo810.me.zzandframe.ui.act;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnViewTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.util.List;

import zhouzhuo810.me.zzandframe.R;
import zhouzhuo810.me.zzandframe.common.cons.ZzConst;
import zhouzhuo810.me.zzandframe.ui.widget.ZzViewPager;

/**
 * 多图浏览
 * Created by zhouzhuo810 on 2017/10/11.
 */
public class MultiImagePreviewActivity extends BaseActivity {

    private ZzViewPager viewPager;
    private boolean defaultBack = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_multi_img_preview;
    }

    @Override
    public boolean defaultBack() {
        return defaultBack;
    }

    @Override
    public void initView() {
        viewPager = (ZzViewPager) findViewById(R.id.view_pager);
    }

    @Override
    public void initData() {

        List<String> imgs = getIntent().getStringArrayListExtra(ZzConst.IMG_PRE_MULTI_PIC_URL);
        defaultBack = getIntent().getBooleanExtra(ZzConst.IMG_PRE_DEFAULT_BACK, true);
        final int placeholderId = getIntent().getIntExtra(ZzConst.IMG_PRE_PLACEHOLDER, -1);
        final int errorPicId = getIntent().getIntExtra(ZzConst.IMG_PRE_ERROR_PIC, -1);
        final boolean crossFade = getIntent().getBooleanExtra(ZzConst.IMG_PRE_CROSS_FADE, true);
        int position = getIntent().getIntExtra(ZzConst.IMG_PRE_MULTI_PIC_POSITION, 0);
        if (imgs != null && imgs.size() > 0) {
            MultiImagePageAdapter adapter = new MultiImagePageAdapter(this, imgs, placeholderId, errorPicId, crossFade);

            adapter.setOnViewTapListener(new OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        onBackPressed();
                    } else {
                        closeAct();
                    }
                }
            });
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(position);
        }
    }

    public static class MultiImagePageAdapter extends PagerAdapter {

        private OnViewTapListener onViewTapListener;
        private Context context;
        private int placeHolderId;
        private int errorPicId;
        private boolean crossFade;

        public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
            this.onViewTapListener = onViewTapListener;
        }

        private List<String> imgs;

        public MultiImagePageAdapter(Context context, List<String> imgs, int placeHolderId, int errorPicId, boolean crossFade) {
            this.context = context;
            this.imgs = imgs;
            this.placeHolderId = placeHolderId;
            this.errorPicId = errorPicId;
            this.crossFade = crossFade;
        }

        @Override
        public int getCount() {
            return imgs == null ? 0 : imgs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());

            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            photoView.setOnViewTapListener(onViewTapListener);
            String url = imgs.get(position);
            if (url.startsWith("http")) {
                DrawableRequestBuilder<String> req = Glide.with(context).load(imgs.get(position))
                        .placeholder(placeHolderId == -1 ? R.drawable.ic_default : placeHolderId)
                        .error(errorPicId == -1 ? R.drawable.ic_default : errorPicId);
                if (crossFade) {
                    req.crossFade();
                }
                req.into(photoView);
            } else {
                DrawableRequestBuilder<File> req = Glide.with(context).load(new File(imgs.get(position)))
                        .placeholder(placeHolderId == -1 ? R.drawable.ic_default : placeHolderId)
                        .error(errorPicId == -1 ? R.drawable.ic_default : errorPicId);
                if (crossFade) {
                    req.crossFade();
                }
                req.into(photoView);
            }
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
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
}