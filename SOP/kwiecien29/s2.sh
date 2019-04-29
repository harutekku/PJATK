#!/bin/bash

p=$1

echo "" >$p

for ((i=2; i<=$#; i++)) ; do
	if `ls -a ${!i} | wc` -lt 12 ; then
		echo $i `ls -a ${!i} | wc` >>$p
	fi
done
echo done
exit 0
