@echo off
echo ============================================
echo   淘宝自动抢购APK构建工具
echo ============================================
echo.
echo 由于本地没有Android开发环境，请选择以下方式：
echo.
echo 1. 使用在线构建服务（推荐）
echo 2. 使用Android Studio构建
echo 3. 使用云构建平台
echo.
set /p choice=请选择构建方式 (1/2/3): 

echo.
if "%choice%"=="1" goto online
if "%choice%"=="2" goto android_studio
if "%choice%"=="3" goto cloud

echo 无效选择，请重新运行脚本
pause
exit

:online
echo.
echo ========== 在线构建方法 ==========
echo 1. 注册GitHub账号（免费）
echo 2. 创建新仓库
echo 3. 上传 pasdi 文件夹中的所有文件
echo 4. 等待GitHub Actions自动构建
echo 5. 在Actions页面下载APK文件
echo.
echo 详细步骤请查看 README_在线构建.md 文件
echo.
pause
exit

:android_studio
echo.
echo ========== Android Studio方法 ==========
echo 1. 下载安装Android Studio（免费）
echo 2. 打开Android Studio
echo 3. 选择 "Open an existing Android Studio project"
echo 4. 选择 pasdi 文件夹
echo 5. 等待项目同步完成
echo 6. 点击 Build → Generate Signed Bundle / APK
echo 7. 按照向导创建签名密钥
echo 8. 构建完成后获取APK文件
echo.
pause
exit

:cloud
echo.
echo ========== 云构建平台方法 ==========
echo 推荐平台：
echo - Appetize.io（在线构建）
echo - Bitrise（免费额度）
echo - Codemagic（免费额度）
echo.
echo 访问相应网站，上传代码即可构建
echo.
pause
exit