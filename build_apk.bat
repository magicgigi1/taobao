@echo off
echo 正在构建淘宝自动抢购APK...

REM 检查是否安装了Android SDK
echo 检查Android SDK环境...
if not exist "%ANDROID_HOME%" (
    echo 错误: 未找到ANDROID_HOME环境变量
    echo 请先安装Android SDK并设置ANDROID_HOME环境变量
    pause
    exit /b 1
)

REM 检查是否安装了Gradle
echo 检查Gradle环境...
gradle --version >nul 2>&1
if errorlevel 1 (
    echo 错误: 未找到Gradle，请先安装Gradle
    pause
    exit /b 1
)

REM 构建APK
echo 开始构建APK...
gradle assembleRelease

if errorlevel 1 (
    echo 构建失败，请检查错误信息
    pause
    exit /b 1
)

echo APK构建成功！
echo APK文件位置: app/build/outputs/apk/release/
echo 请将APK文件安装到手机进行测试
pause