filename=system("echo $filename") 
graphname=system("echo $graphname") 
set xtics("dice 1" 1,"dice 2" 2,"random dice" 3, "markov" 4)
set xrange [0:5]
set terminal png
set output filename.'.png'
set key default
set key box
set key ins vert
set title 'tree size'
plot filename.'.txt'  notitle with errorbars,  "" smooth csplines notitle

