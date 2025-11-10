@echo off
echo 正在修复Android SDK路径配置...
echo.

REM 删除可能错误的local.properties文件
del local.properties 2>nul

REM 创建新的local.properties文件
echo ## This file must *NOT* be checked into Version Control Systems, > local.properties
echo # as it contains information specific to your local configuration. >> local.properties
echo # >> local.properties
echo # Location of the SDK. This is only used by Gradle. >> local.properties
echo # For customization when using a Version Control System, please read the >> local.properties
echo # header note. >> local.properties
echo. >> local.properties
echo # 请根据你的Android Studio安装路径修改下面的路径 >> local.properties
echo # 常见的SDK路径： >> local.properties
echo # Windows: C:\Users\%USERNAME%\AppData\Local\Android\Sdk >> local.properties
echo # 或者: C:\Program Files\Android\Android Studio\plugins\android\lib >> local.properties
echo. >> local.properties
echo # 请打开Android Studio，查看 File -> Project Structure 中的Android SDK location >> local.properties
echo # 然后将正确的路径填写在下面： >> local.properties
echo sdk.dir=C:\Users\%USERNAME%\AppData\Local\Android\Sdk >> local.properties

echo.
echo 已创建local.properties模板文件
echo 请用记事本打开此文件，根据你的实际SDK路径修改sdk.dir的值
echo.
echo 修改完成后，重新在Android Studio中打开项目
echo.
pause