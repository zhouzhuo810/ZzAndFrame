package zhouzhuo810.me.zzandframe.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 系统工具
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class SystemUtils {
    /**
     * 从系统的各种配置文件中读取信息
     *
     * <p>
     * 常用命令：
     * <ul>
     * <li>{@code dhcp.wlan0.ipaddress}     获取IP</li>
     * <li>{@code ro.build.version.release} 系统版本</li>
     * <li>{@code ro.product.brand}         手机品牌</li>
     * <li>{@code ro.product.manufacturer}  手机制造商</li>
     * <li>{@code ro.miui.ui.version.name}  小米系统版本号</li>
     * <li>{@code ro.build.version.emui}    华为系统版本号</li>
     * </ul>
     * </p>
     * @param propName 命令
     * @return 结果
     */
    public static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }

    /**
     * 获取包名，版本号，版本名称等信息
     *
     * @param context 上下文
     * @return 信息
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return new PackageInfo();
    }
}
