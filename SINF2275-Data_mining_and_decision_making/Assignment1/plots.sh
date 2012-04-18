#!/bin/bash
for i in {1..8}
do
    export filename=map${i}
    export graphname=`cat ${filename}.txt | grep -|cut -b 3-`
    echo $graphname
    gnuplot graph.plot
done
