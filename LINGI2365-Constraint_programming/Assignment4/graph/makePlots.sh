#!/bin/bash
for serie in {A,B,C}
do
    for kind in {fail,time}
    do
        export serie=$serie
        export kind=$kind
        echo $graphname
        gnuplot q321.plot
        gnuplot q322.plot
        gnuplot q323.plot
    done
done

