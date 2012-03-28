set title "title"
set yrange [1:1000000]
set xrange [0.6:10000]
set logscale y
set logscale x
set terminal png
set output 'test.png'

plot 'ngrams_data.txt' using 1:4 with boxes title "3-grams", '' using 1:3 with boxes title "2-grams", '' using 1:2 with boxes title "1-grams"
