JFLAGS = -sourcepath ./ -d bin
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

default: main_project

main_project: lib
		$(JC) $(JFLAGS) dk/itu/bmds/teamkolera/src/proj_main/*

lib: taskmanager
		$(JC) $(JFLAGS) dk/itu/bmds/teamkolera/src/taskmanager/*

taskmanager:
		$(JC) $(JFLAGS) dk/itu/bmds/teamkolera/src/lib/*

clean: rm -r bin/*
