
serie=system("echo $serie") 
kind=system("echo $kind") 

set title kind." comparaison knapsack 22 and knapsack 23 serie".serie
set terminal png
set output 'q323_'.kind.'_'.serie.'.png'
set xtics (5,10,15,20)
#plot '../q3/log'.serie.'_'.kind.'.txt' using 2 with line title "knapsack 22" , '' using 3 with line title "knapsack 23"
plot '../q3/log'.serie.'_'.kind.'.txt' using 7 with line title "knapsack 22" , '' using 8 with line title "knapsack 23"
