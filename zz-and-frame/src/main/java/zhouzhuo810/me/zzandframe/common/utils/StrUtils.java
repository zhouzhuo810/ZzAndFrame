package zhouzhuo810.me.zzandframe.common.utils;

import java.util.List;

/**
 * 字符串工具类
 * <p>
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class StrUtils {

    /**
     * 是否为空或长度为0
     *
     * @param text string
     * @return 是／否
     */
    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }


    /**
     * List是否为空或size为0
     *
     * @param list List对象
     * @param <T>  List元素的类型
     * @return 是／否
     */
    public static <T> boolean isListEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }


}
