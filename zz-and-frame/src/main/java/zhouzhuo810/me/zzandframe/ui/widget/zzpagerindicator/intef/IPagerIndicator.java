package zhouzhuo810.me.zzandframe.ui.widget.zzpagerindicator.intef;

import android.support.v4.view.ViewPager;

/**
 * Created by zz on 2016/8/22.
 */
public interface IPagerIndicator {

    /**
     * 绑定ViewPager
     *
     * @param viewPager viewPager
     */
    void setViewPager(ViewPager viewPager);

    /**
     * 跳转到目标页
     *
     * @param position 位置
     * @param animate  是否显示动画
     */
    void setCurrentItem(int position, boolean animate);

}
