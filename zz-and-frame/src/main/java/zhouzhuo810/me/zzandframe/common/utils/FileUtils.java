package zhouzhuo810.me.zzandframe.common.utils;

import java.io.File;

/**
 *
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class FileUtils {

    public static final int NOT_DIR = 1; //路径不是文件夾
    public static final int NO_FILES = 2; //沒有文件
    public static final int DELETE_OK = 3; //全部刪除成功
    public static final int DELETE_NOT_ALL_OK = 4; //刪了一部分

    /**
     * 删除指定文件夹里的所有文件
     * @param dir 文件夹路径
     * @return {@link FileUtils#NOT_DIR},{@link FileUtils#NO_FILES},{@link FileUtils#DELETE_OK},{@link FileUtils#DELETE_NOT_ALL_OK}
     */
    public static int deleteFiles(String dir) {
        File file = new File(dir);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return NO_FILES;
            }
            boolean success = true;
            for (File file1 : files) {
                success &= file1.delete();
            }
            return success ? DELETE_OK : DELETE_NOT_ALL_OK;
        }
        return NOT_DIR;
    }

    /**
     * 复制file1重命名到file2
     * @param filePath1 原文件路径
     * @param filePath2 新文件路径
     * @return 成功返回filePath2, 失败返回null
     */
    public static String renameFile(String filePath1, String filePath2) {
        File file = new File(filePath1);
        if (!file.exists()) {
            return null;
        }
        boolean ok = file.renameTo(new File(filePath2));
        if (ok) {
            return filePath2;
        } else {
            return null;
        }
    }

    /**
     * 获取所有文件
     * @param path 路径
     * @return 所有文件
     */
    public File[] getAllFiles(String path) {
        if (path == null) {
            return null;
        }
        File file = new File(path);
        if (!file.isDirectory()) {
            return null;
        } else {
            return file.listFiles();
        }
    }

}
