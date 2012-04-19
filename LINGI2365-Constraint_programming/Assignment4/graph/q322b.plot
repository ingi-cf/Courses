
serie=system("echo $serie") 
kind=system("echo $kind") 

set title kind." knapsack 22 serie".serie
set terminal png
set output 'q322_'.kind.'_'.serie.'.png'
set xtics (5,10,15,20)
plot '../q3/log'.serie.'_'.kind.'.txt' using 2 with line title "knapsack 22"
