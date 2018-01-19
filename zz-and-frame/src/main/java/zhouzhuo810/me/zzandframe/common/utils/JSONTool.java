package zhouzhuo810.me.zzandframe.common.utils;

/**
 * JSON格式化工具
 * <p>
 * Created by zhouzhuo810 on 2017/8/12.
 */
public class JSONTool {

    /**
     * 格式化JSON字符串
     *
     * @param jsonString JSON字符串
     * @return 格式化后的JSON字符串
     */
    public String formatJsonString(String jsonString) {
        // 计数tab的个数
        int tabNum = 0;
        StringBuilder jsonFormat = new StringBuilder();
        int length = jsonString.length();

        for (int i = 0; i < length; i++) {
            char c = jsonString.charAt(i);
            if (c == '[') {
                tabNum++;
                jsonFormat.append(c + "\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
            } else if (c == ']') {
                tabNum--;
                jsonFormat.append("\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
                jsonFormat.append(c);
            } else if (c == '{') {
                tabNum++;
                jsonFormat.append(c + "\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
            } else if (c == '}') {
                tabNum--;
                jsonFormat.append("\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
                jsonFormat.append(c);
            } else if (c == ',') {
                jsonFormat.append(c + "\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
            } else {
                jsonFormat.append(c);
            }
        }
        return jsonFormat.toString();
    }

    // 是空格还是tab
    private String getSpaceOrTab(int tabNum) {
        StringBuilder sbTab = new StringBuilder();
        for (int i = 0; i < tabNum; i++) {
            sbTab.append("    ");
        }
        return sbTab.toString();
    }
}
