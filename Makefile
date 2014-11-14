build:
	javac src/com/ioopm/*.java
run:
	java -cp ./src com.ioopm.Main

clean:
	rm src/com/ioopm/*.class
