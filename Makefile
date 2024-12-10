CLASSPATH = ./app/src/
TESTCLASSPATH = $(CLASSPATH):Test/:Test/junit4.jar
JFLAGS = -g -cp $(TESTCLASSPATH)
.SUFFIXES: .java .class

TESTSRC = Test/TestRunner.java Test/GameTest.java

SRC = \
	$(CLASSPATH)Game.java \
	$(CLASSPATH)Item.java \
	$(CLASSPATH)Weapon.java

classes: $(SRC:.java=.class)

default: build

%.class: %.java
	javac $(JFLAGS) $<

build:
	gradle build

clean:
	rm -f $(CLASSPATH)*.class Test/*.class

run: classes
	java -cp $(CLASSPATH)Game

test-build: classes Test/TestRunner.class Test/GameTest.class

test: test-build
	java -cp $(TESTCLASSPATH) TestRunner GameTest
