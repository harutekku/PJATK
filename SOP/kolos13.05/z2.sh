#!/bin/bash

for kat in `ls -F | egrep "/$"` ; do
	for plik in `ls $kat` ; do
		if [ -f $kat$plik ] ; then
			if [ `echo $plik | egrep "^.{5,}\." | wc -l` -ge 1 ] ; then
				echo $kat
			fi
		fi
	done
done

exit 0
