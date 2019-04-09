#!/bin/bash 

Katalog=$1

if [ ! -d "$Katalog" ] || [ ! -r "$Katalog" ] ;then
	echo "Blad odczytu katalogu"
fi

cd $Katalog
ls -a | grep -e "^\..*b"
exit 0
