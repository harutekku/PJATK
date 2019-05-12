#!/bin/bash

ifs=$'\n'
for kat in `ls -l | egrep "^dr.*" | rev | cut -d " " -f 1` ; do
	if [ ! -f $kat ] ; then
		touch $kat
		echo $kat >> proc$$$$
	else
		echo "$kat istnieje"
	fi
done

for k in `cat proc$$$$` ; do
	cat proc$$$$ >> $k
done
exit 0
