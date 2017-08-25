package zhouzhuo810.me.zzandframe.common.rx;

/**
 * Created by zz on 2016/9/18.
 */
public class ExitEvent {

    private ExitEvent(){}

    public static ExitEvent exitEvent;

    public static ExitEvent getInstance() {
        if (exitEvent == null) {
            exitEvent = new ExitEvent();
        }
        return exitEvent;
    }
}
