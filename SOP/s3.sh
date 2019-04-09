#!/bin/bash 

Katalog=$1
Plik=$2
touch $Plik

if [ ! -d "$Katalog" ] || [ ! -r "$Plik" ] ;then
	echo "Blad odczytu katalogu lub pliku"
	exit 1
fi


cd $Katalog
ls -a -l | tr -s ' ' | cut -d ' ' -f 7,10 | grep "." | sort > $Plik
exit 0
