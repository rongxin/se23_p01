echo "find  src files to be compiled"
find src/ -iname *.java > files.txt
dir /S src\*.java > files.txt
cat files.txt
echo "start to compile"
for /F "tokens=1" %%i in (files.txt) do javac -d classes\ %%i
echo "compile finished"
del files.txt

echo "copying icon files"
mkdir classes\sg\edu\nus\iss\shop\ui\icons\
copy \sg\edu\nus\iss\shop\ui\icons\*  classes\sg\edu\nus\iss\shop\ui\icons\
