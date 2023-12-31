@echo origin-platform

set BASE_DIR=%~dp0
set CLONE_DIR=%BASE_DIR%..\origin-platform

mkdir %CLONE_DIR%
copy .\pom.xml %CLONE_DIR%\pom.xml
cd %CLONE_DIR%

rem ==== origin-build source clone start
git clone -b master https://e.coding.net/jyusun/platform/origin-build.git .\origin-build

rem ==== origin-boot-starters source clone start
git clone -b master https://e.coding.net/jyusun/platform/origin-boot-starters.git .\origin-boot-starters


pause