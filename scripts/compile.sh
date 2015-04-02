./setenv.sh
echo "find  src files to be compiled"
find src/ -iname *.java > files.txt
cat files.txt
echo "start to compile"
javac -d classes/ @files.txt
echo "compile finished"
rm files.txt

echo "copying icon files"
mkdir -p classes/sg/edu/nus/iss/shop/ui/icons/
cp src/sg/edu/nus/iss/shop/ui/icons/*  classes/sg/edu/nus/iss/shop/ui/icons/
