JFLAGS = -sourcepath ./ -d bin
JC = javac
.SUFFIXES: .java .class

default: main

main: lib
	$(JC) $(JFLAGS) dk/itu/bmds/teamkolera/src/proj_main/*

lib: taskmanager
	$(JC) $(JFLAGS) dk/itu/bmds/teamkolera/src/lib/*

taskmanager: 
	$(JC) $(JFLAGS) dk/itu/bmds/teamkolera/src/taskmanager/*


clean: 
	rm -r bin/*
