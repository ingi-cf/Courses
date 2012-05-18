dir .\*.java /s /B > sources_list.txt
javac -classpath .\postal\GTools.jar @sources_list.txt
