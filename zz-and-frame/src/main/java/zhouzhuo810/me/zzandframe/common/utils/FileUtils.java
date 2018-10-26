package zhouzhuo810.me.zzandframe.common.utils;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * 文件操作工具类
 * <p>
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class FileUtils {

    public static final int NOT_DIR = 1; //路径不是文件夾
    public static final int NO_FILES = 2; //沒有文件
    public static final int DELETE_OK = 3; //全部刪除成功
    public static final int DELETE_NOT_ALL_OK = 4; //刪了一部分

    /**
     * 删除指定文件夹里的所有文件
     *
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
     *
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
     *
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


    /**
     * Uri转path
     *
     * @param context 上下文
     * @param uri     Uri
     * @return file path
     */
    public static String getRealFilePath(Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }

                }
                cursor.close();
            }
            if (data == null) {
                data = getImageAbsolutePath(context, uri);
            }

        }
        return data;
    }

    /**
     * file path转Uri
     *
     * @param context   上下文
     * @param filePath  文件路径
     * @param authority applicationId + .provider
     * @return Uri
     */
    public static Uri filePathToUri(Context context, final String filePath, final String authority) {
        if (Build.VERSION.SDK_INT > 23) {
            return FileProvider.getUriForFile(context, authority,
                    new File(filePath));
        } else {
            return Uri.fromFile(new File(filePath));
        }
    }

    /**
     * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
     *
     * @param context  上下文
     * @param imageUri 图片Uri
     * @return 文件路径
     */
    @TargetApi(19)
    public static String getImageAbsolutePath(Context context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
