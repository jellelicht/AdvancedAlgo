#!/usr/bin/env bash

OUTPUT='./output'
PS=(0.5 1)
NODES=$(seq 1 4 70)
WS=$(seq 1 4 70)
# TS=$(seq 1 70)

rm "$OUTPUT"/*

for p in "${PS[@]}"
do
    for w in $WS
    do
        for n in $NODES
        do
            CMD="java GraphGenerator $n $p $w 1"
            echo "$CMD"
            java GraphGenerator $n $p $w 1
            mv ./max* "$OUTPUT"
        done
    done
done

