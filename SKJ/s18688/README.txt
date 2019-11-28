Aby uruchomić program należy upewnić się że w systemie mamy dodaną zmienną PATH do javy, jeśli nie mamy lub nie chcemy musimy w plikach compile i run dodać pełną ścieżkę do javy. Następnie kompilujemy program za pomocą pliku compile, oraz uruchamiamy za pomocą pliku run. Aby zakończyć działanie programu używamy ctrl+c.

Program czasami łapie wyjątki które nie wpływają na działanie programu ani na poprawne załadowanie stron u klienta, dlatego zostały wykomentowane.

Program posiada licznik nowych połączeń i logi wskazujące na nowe zapytania GET.

Program obsługuje wielowątkowość, zarówno jeśli chodzi o ilość klientów jak i o wiele zapytań od tego samego klienta względem tego samego połączenia.

Opis:
Po uruchomieniu programu tworzony jest nowy obiekt klasy ServerSocket, który nasłuchuje nowych połączeń. Każde połączenie tworzy nowy wątek obsługujący dane zapytanie. Jeśli w zapytaniu znajduje się linia "Proxy-Connection" zostaje ona pominięta, ze wzgledu na połączenia HTTP. Zapytanie otwiera nowe połączenie do serwera, na odpowiednim porcie. Następnie zestawiane są dwa połączenia, jedno odbierające dane z serwera i przesyłające do serwera w postaci oddzielnego wątku, a drugie odbierające dane z klienta i przekazujące do serwera w postaci pętli. Gdy wszystko zostanie przekazane program zamyka związane z danym połączeniem wątki i oczekuje na dalsze połączenia.
