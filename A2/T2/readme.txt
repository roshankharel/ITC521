Compile

javac --module-path library\javafx-sdk-11.0.2\lib\ --add-modules=ALL-MODULE-PATH src\main\*.java -d out

Run

java --module-path library\javafx-sdk-11.0.2\lib\ --add-modules=ALL-MODULE-PATH -cp out main.Window