# OpenAndroidMVC
基于Android的MVC架构模式，开源一个Android快速开发库。

## 使用方法
### 1.将其添加到项目的根build.gradle中：
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
### 2.添加依赖
```
dependencies {
	  implementation 'com.github.wuyubo0522:OpenAndroidMVC:1.0.4'
}
```

### 版本说明
#### 1.0.5
这个版本待定，其他遇到更多的需求，完善我的MVC架构模式的开源库
#### 1.0.4
修改BaseFragment出现的bug。
#### 1.0.3
修复小刀注解出现的bug。
#### 1.0.2
修复了1.0.1中BaseActivity的bug。
#### 1.0.1
这个版本主要是集成了腾讯的X5内核，自定义了X5WebView，让用户在App内部访问网页速度加快。
#### 1.0.0
主要创建了OpenAndroidMVC库，库里面创建了base、utils包，涵盖了BaseActivity、BaseFragment，依赖了小刀注解。日常开发用到的工具类。
