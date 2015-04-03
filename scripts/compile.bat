call setenv.bat

echo "find  src files to be compiled"

cd src\
dir /s /B *.java > ..\files.txt
cd ..

rd  classes\ /s /q
mkdir classes\

echo "start to compile"

javac -classpath lib\* -d classes @files.txt

echo "compile finished"

del files.txt

echo "copying icon files"
mkdir classes\sg\edu\nus\iss\shop\ui\icons\
copy src\sg\edu\nus\iss\shop\ui\icons\*.png  classes\sg\edu\nus\iss\shop\ui\icons\

echo "Initialize data files"
java -cp classes\  sg.edu.nus.iss.shop.dao.InitApplicationRepository
