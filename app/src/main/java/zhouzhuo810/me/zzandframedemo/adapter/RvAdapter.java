package zhouzhuo810.me.zzandframedemo.adapter;

import android.content.Context;

import java.util.List;

import zhouzhuo810.me.zzandframe.ui.adapter.RvAutoBaseAdapter;
import zhouzhuo810.me.zzandframedemo.R;
import zhouzhuo810.me.zzandframedemo.bean.RvBean;

/**
 * Created by zhouzhuo810 on 2017/9/14.
 */

public class RvAdapter extends RvAutoBaseAdapter<RvBean> {

    public RvAdapter(Context context, List<RvBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.list_item_rv;
    }

    @Override
    protected void fillData(ViewHolder holder, RvBean item, int position) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_phone, item.getPhone());
    }
}
