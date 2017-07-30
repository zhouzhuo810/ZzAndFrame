package zhouzhuo810.me.zzandframedemo;

import android.content.Context;

import zhouzhuo810.me.zzandframe.ui.app.BaseApplication;

/**
 *
 * Created by admin on 2017/7/25.
 */
public class MyApplication extends BaseApplication {

    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
