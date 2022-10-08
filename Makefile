build: clean
	mvn package -X

run: build
	java -ea -cp target/tick-tack-tick-1.0.jar com.datawater.ticktacktick.App

doc:
	mvn javadoc:javadoc

clean:
	mvn clean
