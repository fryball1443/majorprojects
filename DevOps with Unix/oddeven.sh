#!/bin/bash

for ((i=1; i<9999; i++))
do
  echo $i
done

if [ $(( $(date +%s) % 2 )) -eq 0 ]
then 
  echo "the time is odd"
else 
  echo "the time is even"
fi

