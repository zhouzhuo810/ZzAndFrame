# ZzAndFrame
简易Android开发框架

## 用法

project/build.gradle添加

```
allprojects {
	repositories {
		...
		maven {
		    url "https://jitpack.io"
		}
		maven {
		    url "https://maven.google.com"
		}
	}
}
```

app/build.gradle添加

```
    compile 'com.github.zhouzhuo810:ZzAndFrame:1.2.6'
```

添加上面依赖即同时依赖下面内容：
```
    compile 'com.android.support:appcompat-v7:27.0.1'
    compile 'com.android.support:recyclerview-v7:27.0.1'
    //retrofit
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    //string
    compile 'com.squareup.retrofit2:converter-scalars:2.1.0'
    //gson
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    //rxjava
    compile 'io.reactivex:rxjava:1.1.9'
    compile 'io.reactivex:rxandroid:1.2.1'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    //okhttp
    compile 'com.squareup.okhttp3:okhttp:3.4.1'
    compile 'com.squareup.okhttp3:okhttp-urlconnection:3.4.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'
    //glide
    compile 'com.github.bumptech.glide:glide:3.8.0'
    //andpermission
    compile 'com.yanzhenjie:permission:2.0.0-alpha'
    //material design
    compile 'com.android.support:design:27.0.1'
```

## 说明

- 所有控件或布局中均使用px作为单位，以UI设计图为准。
- AndroidManifest.xml中需加入如下UI设计图的尺寸。
```xml
<meta-data android:name="design_width" android:value="768">
</meta-data>
<meta-data android:name="design_height" android:value="1280">
</meta-data>
```
常用尺寸
```xml
<meta-data android:name="design_width" android:value="1080">
</meta-data>
<meta-data android:name="design_height" android:value="1920">
</meta-data>
```


- 使用图片预览功能时，需要Activity注册

```xml
        <activity android:name="zhouzhuo810.me.zzandframe.ui.act.ImagePreviewActivity" />
        <activity android:name="zhouzhuo810.me.zzandframe.ui.act.MultiImagePreviewActivity" />
```

- 使用DisplayUtils#previewImage和#previewImages方法可以兼容转场动画

```java
    DisplayUtils.previewImage((Activity) context, iv, url, -1, true);
```

```java
    DisplayUtils.previewImages((Activity) context, iv, urls, 0, -1, true);
```
- 新集成AndPermission功能；
- 继承BaseActivity可以选择图片和拍照；


## 控件简介

### MarkView

- 角标控件

### TitleBar

- 标题栏控件

### TabBar

- 底部菜单导航控件

### ZzPagerIndicator

- ViewPager指示器

## Base类简介

### BaseActivity

- 基类Activity

### BaseFragment

- 基类Fragment

### BaseApplication

- 基类Application


## utils简介

### ToastUtils

- 吐司工具类

### StrUtils

- 字符串处理类

### DateUtils

- 日期处理类

### FileUtils

- 文件处理类

### NoticeUtils

- 通知工具类

### CopyUtils

- 复制粘贴工具

### JSONTool

- Json格式化工具

### EncodeUtils

- 编解码工具类
 
### EncryptUtils

- 加解密工具类
