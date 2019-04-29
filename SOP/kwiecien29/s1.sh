#!/bin/bash

for i in `ls` ;
do
	if cat $i | grep -v "^/\*main\*/" >/dev/null  ; then
		echo $i
	fi
	
done
echo done
exit 0
