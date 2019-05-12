#!/bin/bash



for plik in $(ls) ; do


if [ $(cat "$plik" | grep "/\*main\*/" | wc -l ) -lt 1 ] ; then 
	
echo "$plik"

fi


done

