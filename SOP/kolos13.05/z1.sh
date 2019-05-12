#!/bin/bash

ilelinii=$(cat $1 | head -n 1 | wc -w)
echo -n > pomo

for((i=1; i<=ilelinii; i++)) ; do
	for cyfra in `cat $1 | cut -d " " -f $i` ; do
		echo -n "$cyfra ">> pomo
	done
	echo >> pomo
done
cat pomo > $1
exit 0
