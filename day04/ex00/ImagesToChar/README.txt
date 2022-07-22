#0. Delete old directory:
rm -rf target

#1. Create directory:
mkdir target

#2. Compile files to the 'target' directory:
javac -d target src/java/edu/school21/printer/*/*.java

#3. Run program:
java -classpath target edu.school21.printer.app.Program . 0 /Users/kferterb/Downloads/it.bmp