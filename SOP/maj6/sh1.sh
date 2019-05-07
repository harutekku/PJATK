#!/bin/bash

for i in `ls -a -l -G  /etc | egrep "^d" | tr -s ' ' | cut -d' ' -f 8-` ;
do
	if [ $( ls -l -A /etc/$i | egrep "^.r.*" | wc -l) -gt 0 ] ; then
		echo $i
	fi
#echo $i
done

#ls /etc -l -a | grep ".r.*"


