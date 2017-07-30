package zhouzhuo810.me.zzandframe.ui.app;

import android.app.Application;

/**
 * Created by admin on 2017/7/30.
 */

public abstract class BaseApplication extends Application {

    private static BaseApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static Application getBaseInstance() {
        return INSTANCE;
    }
}
