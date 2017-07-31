package zhouzhuo810.me.zzandframedemo.adapter;

import android.content.Context;

import java.util.List;

import zhouzhuo810.me.zzandframe.ui.adapter.LvAutoBaseAdapter;
import zhouzhuo810.me.zzandframedemo.R;
import zhouzhuo810.me.zzandframedemo.bean.LvBean;

/**
 *
 * Created by admin on 2017/7/31.
 */
public class LvAdapter extends LvAutoBaseAdapter<LvBean> {

    public LvAdapter(Context context, List<LvBean> data) {
        super(context, data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.list_item_lv;
    }

    @Override
    protected void fillData(ViewHolder holder, LvBean item, int position) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_phone, item.getPhone());
    }
}
