
serie=system("echo $serie") 
kind=system("echo $kind") 

set title kind." comparaison knapsack 21 and knapsack 22 serie".serie
set logscale y
set terminal png
set output 'q322_'.kind.'_'.serie.'.png'
set xtics (5,10,15,20)
plot '../q3/log'.serie.'_'.kind.'.txt' using 3 with line title "knapsack 21" , '' using 7 with line title "knapsack 22"
