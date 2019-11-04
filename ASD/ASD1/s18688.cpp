#include <iostream>
#include <fstream>

using namespace std;
int main(int args, char **argv) {
    ifstream file;
    file.open(argv[1]);
    if (file.is_open()){
        int first;
        file>>first;

        int tempSumAsc = first, tempSumDesc=first;
        int sumAsc=first,sumDesc=first;
        int tempCountAsc=1,tempCountDesc=1;
        int countAsc=1,countDesc=1;
        int previous=first,next;

        while(!file.eof()){
            file >> next;
            if(next>previous){
                tempCountAsc++;
                tempSumAsc+=next;
                tempCountDesc=1;
                tempSumDesc=next;
                if(tempCountAsc>=countAsc){
                    countAsc=tempCountAsc;
                    sumAsc=tempSumAsc;
                }
            }
            else if(next==previous){
                tempCountAsc++;
                tempCountDesc++;
                tempSumAsc+=next;
                tempSumDesc+=next;
                if(tempCountAsc>=countAsc){
                    countAsc=tempCountAsc;
                    sumAsc=tempSumAsc;
                }
                if(tempCountDesc>=countDesc){
                    countDesc=tempCountDesc;
                    sumDesc=tempSumDesc;
                }
            }
            else{
                tempCountDesc++;
                tempSumDesc+=next;
                tempCountAsc=1;
                tempSumAsc=next;
                if(tempCountDesc>=countDesc){
                    countDesc=tempCountDesc;
                    sumDesc=tempSumDesc;
                }
            }
            previous=next;
        }
        if(countAsc>countDesc)cout<<countAsc<<" "<<sumAsc<<endl;
        else cout<<countDesc<<" "<<sumDesc<<endl;
    }
    else cerr<<"Unable to open the file"<<endl;

    return 0;
}