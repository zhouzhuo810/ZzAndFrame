package zhouzhuo810.me.zzandframe.common.utils;

import java.util.List;

/**
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class StrUtils {

    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }


    public static <T> boolean  isListEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }


}
