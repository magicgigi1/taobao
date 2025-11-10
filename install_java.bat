@echo off
echo ============================================
echo          Java环境自动安装配置工具
echo ============================================
echo.

echo 步骤1: 检查当前Java环境
echo.
java -version >nul 2>&1
if %errorlevel% == 0 (
    echo ✅ Java已安装
echo.
    java -version
echo.
    goto build_apk
) else (
    echo ❌ 未检测到Java环境
    echo.
)

echo 步骤2: 下载Java JDK 11
echo.
echo 请手动下载Java JDK 11:
echo.
echo 推荐下载链接:
echo 1. OpenJDK (免费开源):
echo    https://github.com/adoptium/temurin11-binaries/releases/download/jdk-11.0.23%2B9/OpenJDK11U-jdk_x64_windows_hotspot_11.0.23_9.msi
echo.
echo 2. Oracle JDK (官方版本):
echo    访问: https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html
echo.
echo 请下载完成后继续...
echo.
pause

echo 步骤3: 设置环境变量
echo.
echo 正在设置JAVA_HOME环境变量...

REM 尝试自动检测Java安装路径
set "java_path="
for /d %%i in ("C:\Program Files\Java\jdk-11*") do set "java_path=%%i"

if "%java_path%"=="" (
    echo ❌ 未自动检测到Java安装路径
    echo.
    echo 请手动设置环境变量:
    echo 1. 右键"此电脑" -> "属性" -> "高级系统设置"
    echo 2. 点击"环境变量"
    echo 3. 在"系统变量"中新建:
    echo    变量名: JAVA_HOME
    echo    变量值: C:\Program Files\Java\jdk-11.0.23 (根据实际路径)
    echo 4. 修改Path变量，添加: %%JAVA_HOME%%\bin
    echo.
    pause
) else (
    echo ✅ 检测到Java路径: %java_path%
    setx JAVA_HOME "%java_path%" /M
    echo ✅ 已设置JAVA_HOME环境变量
)

echo 步骤4: 验证Java安装
echo.
java -version >nul 2>&1
if %errorlevel% == 0 (
    echo ✅ Java安装成功!
echo.
    java -version
echo.
) else (
    echo ❌ Java安装失败，请检查环境变量设置
    echo.
    pause
    exit /b 1
)

:build_apk
echo 步骤5: 构建APK
echo.
echo 开始构建淘宝自动抢购APK...
echo.

cd /d "%~dp0"

REM 检查Gradle环境
."gradlew.bat" --version >nul 2>&1
if %errorlevel% == 0 (
    echo ✅ Gradle环境正常
) else (
    echo ❌ Gradle环境异常
    echo.
    pause
    exit /b 1
)

REM 构建APK
echo 正在构建APK，请稍候...
echo.
."gradlew.bat" assembleRelease

if %errorlevel% == 0 (
    echo.
    echo ✅ APK构建成功!
echo.
    echo APK文件位置:
    echo %~dp0app\build\outputs\apk\release\app-release.apk
echo.
    echo 请将此APK文件发送到手机安装使用
echo.
) else (
    echo.
    echo ❌ APK构建失败，请检查错误信息
echo.
)

pause