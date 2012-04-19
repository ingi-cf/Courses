#!/bin/bash
for serie in A
do
    for kind in {fail,time}
    do
        export serie=$serie
        export kind=$kind
        echo $graphname
        gnuplot q321.plot
        gnuplot q322.plot
    done
done

for serie in {B,C}
do
    for kind in {fail,time}
    do
        export serie=$serie
        export kind=$kind
        echo $graphname
        gnuplot q323.plot
    done
done

