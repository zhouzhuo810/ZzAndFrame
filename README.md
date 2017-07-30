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
    compile 'com.github.zhouzhuo810:ZzAndFrame:1.0.3'
```

## 控件简介

### MarkView

- 角标控件

### TitleBar

- 标题栏控件

### TabBar

- 底部菜单导航控件


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