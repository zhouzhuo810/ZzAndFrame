package zhouzhuo810.me.zzandframedemo;

import android.app.Application;

/**
 *
 * Created by admin on 2017/7/25.
 */
public class MyApplication extends Application {

    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static MyApplication getINSTANCE() {
        return INSTANCE;
    }
}
