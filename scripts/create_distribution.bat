set DIST_PATH=..\dist\se23_p01
set PROJECT_PATH=..

echo "creating distribution folder %DIST_PATH%"
rd  %DIST_PATH% /s /q
mkdir %DIST_PATH%\docs

echo "copy source code to distribution folder"
xcopy %PROJECT_PATH%\src %DIST_PATH%\src  /s /e /i
xcopy %PROJECT_PATH%\test %DIST_PATH%\test  /s /e /i
xcopy %PROJECT_PATH%\lib %DIST_PATH%\lib /s /e /i


echo "create data and classes folder"
mkdir  %DIST_PATH%\data
mkdir  %DIST_PATH%\classes

echo "copying scripts to distribution"
xcopy %PROJECT_PATH%\scripts\compile.bat %DIST_PATH% 
xcopy %PROJECT_PATH%\scripts\setenv.bat %DIST_PATH% 
xcopy %PROJECT_PATH%\scripts\run.bat %DIST_PATH% 

