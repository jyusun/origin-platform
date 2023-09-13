@echo origin-platform

set BASE_DIR=%~dp0
set CLONE_DIR=%BASE_DIR%..\origin-platform

cd %CLONE_DIR%

rem ==== origin-build source git pull start
git pull -b master https://e.coding.net/jyusun/platform/origin-build.git .\origin-build

rem ==== origin-boot-starters source git pull start
git pull -b master https://e.coding.net/jyusun/platform/origin-boot-starters.git .\origin-boot-starters


pause