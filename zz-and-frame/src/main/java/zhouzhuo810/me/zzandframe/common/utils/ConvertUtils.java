package zhouzhuo810.me.zzandframe.common.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class ConvertUtils {

    /**
     * String集合转String数组
     * @param list 集合
     * @return 数组
     */
    public static String[] listToString(List<String> list) {
        if (list == null) {
            return null;
        }
        String [] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * String数组转String集合
     * @param strings 数组
     * @return 集合
     */
    public static List<String> stringToList(String[] strings) {
        if (strings == null) {
            return null;
        }
        return Arrays.asList(strings);
    }
}
