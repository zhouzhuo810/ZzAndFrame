# ZzAndFrame
简易Android开发框架

## 用法

project/build.gradle添加

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

app/build.gradle添加

```
    compile 'com.github.zhouzhuo810:ZzAndFrame:1.1.0'
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
- 继承BaseActivity可以


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