mkdir bin/main
mkdir bin/main/images

echo Installing...
cp -r src/main/images/* bin/main/images/

javac -d bin/main -classpath bin/main ./src/main/application/*.java ./src/main/layout/*.java ./src/main/database/*.java ./src/main/database/entity/*.java ./src/main/database/dao/*.java ./src/main/database/dao/impl/*.java
java -classpath bin/main application.Init
jar cfm bank-system.jar ./MANIFEST/MANIFEST.MF -C bin/main .
echo You have successfully installed the Admininster Version!

echo The installation has completed.
exit
