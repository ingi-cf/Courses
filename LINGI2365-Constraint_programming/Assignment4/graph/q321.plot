
serie=system("echo $serie") 
kind=system("echo $kind") 

set title kind." comparaison knapsack 21 with heuristics serie".serie
set terminal png
set output 'q321_'.kind.'_'.serie.'.png'
set xtics (5,10,15,20)
plot '../q3/log'.serie.'_'.kind.'.txt' using 2 with line title "labelFF"    \
     , '' using 3 with line title "heavier first"                           \
     , '' using 4 with line title "usefullness / weight"                   \
     , '' using 5 with line title "usefullness"                             \
     , '' using 6 with line title "id"
