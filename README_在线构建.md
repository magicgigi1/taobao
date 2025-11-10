# 淘宝自动抢购APK - 在线构建指南

## 🚀 最简单的方法：GitHub Actions自动构建

### 第一步：准备GitHub账号
1. 访问 [GitHub官网](https://github.com)
2. 注册免费账号（如果还没有）
3. 登录GitHub

### 第二步：创建新仓库
1. 点击右上角"+" → "New repository"
2. 输入仓库名称：`taobao-autobuy-app`
3. 选择"Public"（公开，免费）
4. 勾选"Add a README file"
5. 点击"Create repository"

### 第三步：上传项目文件
1. 在仓库页面，点击"Add file" → "Upload files"
2. 将 `e:\代码的临时\pasdi` 文件夹中的所有文件拖拽到上传区域
3. 包括以下重要文件：
   - `app/` 文件夹
   - `build.gradle`
   - `settings.gradle`
   - `gradlew.bat`
   - `.github/workflows/build.yml`
4. 点击"Commit changes"

### 第四步：等待自动构建
1. 上传完成后，GitHub会自动开始构建
2. 点击仓库顶部的"Actions"标签
3. 查看构建进度
4. 构建完成后（约5-10分钟），点击构建任务
5. 在"Artifacts"部分下载APK文件

### 第五步：安装到手机
1. 将下载的APK文件发送到手机
2. 在手机文件管理器中找到APK
3. 点击安装，允许"未知来源"
4. 完成安装

## 📱 其他在线构建方法

### 方法二：使用Appetize.io
1. 访问 [Appetize.io](https://appetize.io)
2. 上传项目代码
3. 在线构建并获取APK下载链接

### 方法三：使用Bitrise
1. 注册 [Bitrise](https://bitrise.io) 账号
2. 连接GitHub仓库
3. 配置Android构建流程
4. 自动构建并分发APK

## 🔧 本地构建方法（需要安装环境）

### 安装Android Studio
1. 下载 [Android Studio](https://developer.android.com/studio)
2. 安装并启动
3. 打开 `e:\代码的临时\pasdi` 项目
4. 构建 → Generate Signed Bundle/APK

### 使用命令行（需要Java和Android SDK）
```bash
cd e:\代码的临时\pasdi
./gradlew assembleRelease
```

## ⚠️ 注意事项

### 构建成功标志
- GitHub Actions显示绿色对勾
- 在Artifacts中看到APK文件
- 文件大小约10-20MB

### 常见问题
1. **构建失败**：检查错误信息，通常是配置问题
2. **APK无法安装**：确保手机允许"未知来源"安装
3. **应用闪退**：检查Android版本兼容性

### 安全提示
- 只从可信来源下载APK
- 安装前扫描病毒
- 定期更新应用版本

## 📞 技术支持

如果构建过程中遇到问题：
1. 查看GitHub Actions的错误日志
2. 检查项目文件是否完整上传
3. 确保所有依赖文件都在仓库中

---

**提示**：GitHub Actions是最推荐的构建方式，完全免费且自动化，无需本地环境配置。