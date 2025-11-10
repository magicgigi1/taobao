# 如何生成APK文件

## 方法一：使用Android Studio（推荐）

### 1. 安装Android Studio
1. 下载并安装 Android Studio
2. 安装必要的SDK和工具

### 2. 导入项目
1. 打开 Android Studio
2. 选择 "Open an existing Android Studio project"
3. 选择 `e:\代码的临时\pasdi` 文件夹
4. 等待项目同步完成

### 3. 构建APK
1. 点击菜单 Build → Generate Signed Bundle / APK
2. 选择 APK
3. 创建新的密钥库（或使用现有）
4. 选择构建类型为 Release
5. 点击 Finish 开始构建

### 4. 获取APK
构建完成后，APK文件位于：
`app/build/outputs/apk/release/app-release.apk`

## 方法二：使用命令行

### 1. 安装必要环境
- Java JDK 8+
- Android SDK
- Gradle

### 2. 构建APK
```bash
cd e:\代码的临时\pasdi
./gradlew assembleRelease
```

### 3. 获取APK
APK文件位于：
`app/build/outputs/apk/release/app-release.apk`

## 方法三：使用在线构建服务

### 1. GitHub Actions
将项目上传到GitHub，配置自动构建

### 2. 第三方构建平台
- App Center
- Bitrise
- CircleCI

## 📱 测试安装

### 在模拟器中测试
1. 在Android Studio中启动模拟器
2. 将APK拖拽到模拟器安装
3. 测试应用功能

### 在真机中测试
1. 开启手机的"开发者选项"
2. 开启"USB调试"
3. 连接手机到电脑
4. 在Android Studio中运行应用到手机

## 🔧 常见问题

### 构建失败
- 检查Android SDK版本
- 确认Gradle配置正确
- 检查依赖库版本兼容性

### 安装失败
- 检查Android版本兼容性
- 确认签名配置正确
- 检查权限设置

### 功能异常
- 测试悬浮窗口权限
- 检查淘宝应用是否安装
- 验证网络连接

## 📦 发布准备

### 测试清单
- [ ] 基本功能测试
- [ ] 权限测试
- [ ] 兼容性测试
- [ ] 性能测试

### 发布前检查
- [ ] 应用图标和名称
- [ ] 版本号和版本名称
- [ ] 权限说明
- [ ] 隐私政策

## 🚀 发布渠道

### 应用商店
- 华为应用市场
- 小米应用商店
- 腾讯应用宝
- 360手机助手

### 直接分发
- 官方网站下载
- 网盘分享
- 二维码扫描下载

---

**注意**：发布前请确保应用符合相关法律法规和应用商店政策。