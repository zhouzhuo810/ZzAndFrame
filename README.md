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
    implementation 'com.github.zhouzhuo810:ZzAndFrame:1.3.5'
```

添加上面依赖即同时依赖下面内容：
```
    api 'com.android.support:appcompat-v7:28.0.0'
    api 'com.android.support:recyclerview-v7:28.0.0'
    //retrofit
    api 'com.squareup.retrofit2:retrofit:2.5.0'
    //string
    api 'com.squareup.retrofit2:converter-scalars:2.1.0'
    //gson
    api 'com.squareup.retrofit2:converter-gson:2.5.0'
    //rxjava
    api 'io.reactivex:rxjava:1.1.9'
    api 'io.reactivex:rxandroid:1.2.1'
    api 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    //okhttp
    api 'com.squareup.okhttp3:okhttp:3.12.0'
    api 'com.squareup.okhttp3:okhttp-urlconnection:3.10.0'
    api 'com.squareup.okhttp3:logging-interceptor:3.11.0'
    //glide
    api 'com.github.bumptech.glide:glide:3.8.0'
    //andpermission
    api 'com.yanzhenjie:permission:2.0.0-rc12'
    //material design
    api 'com.android.support:design:28.0.0'
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


### 版本日志

> v1.3.5

- 修改RvBaseAdapter的数组越界bug；

> v1.3.4

- 修复ZzPagerIndicator控件的滚动逻辑和若干bug；

> v1.3.2

- 修改了DisplayUtils多图预览时Activity跳转错误问题；
- 修复ISearch接口设计不合理问题；
- 更新gradle和第三方库版本；

> v1.2.8

- 修改ZzPagerIndicator添加水平图文模式和图片未选中隐藏模式；

> v1.2.7

- 修改默认Activity打开和关闭动画；


