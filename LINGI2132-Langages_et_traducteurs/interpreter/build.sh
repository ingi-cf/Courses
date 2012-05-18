#!/bin/bash
find -name "*.java" > sources_list.txt
javac -classpath ./postal/GTools.jar @sources_list.txt
