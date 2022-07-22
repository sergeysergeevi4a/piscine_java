#0. Delete old directory:
rm -rf target

#1. Create new directory:
mkdir target

#2. Compile files to the 'target' directory:
javac -d target src/java/edu/school21/printer/*/*.java

#3. Copy resources to the 'target':
cp -R src/resources target/.

#4. Create archive .jar to the 'target':
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .
chmod u+x target/images-to-chars-printer.jar

#5. Run program
java -jar target/images-to-chars-printer.jar . 0