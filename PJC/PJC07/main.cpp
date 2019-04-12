#include <iostream>

using std::cout, std::cin, std::endl;

void showTab(int **tab,int size){
    for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
            cout<<tab[i][j]<<" ";
        }
        cout<<endl;
    }
    cout<<endl;
}
void showTab(int *tab,int size){
    for(int i=0;i<size;i++){
        cout<<tab[i]<<" ";
    }
    cout<<endl;
}
void showTab(char **tab,int size){
    for(int i=0;i<size;i++){
        cout<<tab[i]<<endl;
    }
    cout<<endl;
}

void swapTab(int **tab, int x1, int x2){
    std::swap(tab[x1],tab[x2]);
}

int** multiDimensionOGABOOGA(){
    int** tab=new int*[5];
    for(int i=0;i<5;i++){
        if(i==3){
            tab[i]=tab[i-1];
            continue;
        }
        tab[i]=new int[5];
        for(int j=0;j<5;j++){
            tab[i][j]=i+j;
        }
    }
    return tab;
}

void generateValues(int *tab, int n, int min, int max){
    for(int i=0;i<n;i++){
        tab[i]=rand()%(max-min)+min;
        bool repeat=false;
        for(int j=0;j<i;j++){
            if(tab[i]==tab[j])repeat=true;
        }
        if(repeat)i--;
    }
}

char** returnWords(const char* word, int n){
    int space=0;
    for(int i=0;i<n;i++){
        if(word[i]==' ')space++;
    }
    space++;
    cout<<space<<endl;
    char** words=new char*[space];
    int secondIndex=0;
    int i=0;
    while(i<space){
        words[i]="";
        //cout<<"don"<<endl;
        while(secondIndex<n){
            cout<<i<<" "<<secondIndex<<endl;
            if(word[secondIndex]!=' '){
                words[i]+=word[secondIndex];
                secondIndex++;
            }
            else{
                words[i]+='\0';
                secondIndex++;
                break;
            }
        }
        i++;
    }
    /*for(int i=0;i<space;i++){
        words[i]="";
        while(secondIndex<n){
            cout<<"debuger hehe "<<i<<endl;
            if(word[secondIndex]!=' '){
                words[i]+=word[secondIndex];
                i++;
                //break;
            }
            else{
                words[i]+='\0';
                cout<<"no powinno przerwac "<<i<<endl;
            }
            secondIndex++;
        }
    }*/
    return words;
}

int main() {
    int size=5;
    //cin>>size;
    int** tab=new int*[size];
    for(int i=0;i<size;i++){
        //cout<<i<<" i"<<endl;
        tab[i]=new int[size];
        for(int j=0;j<size;j++){
            tab[i][j]=i+j;
            //cout<<j<<" j"<<endl;
        }
    }
    showTab(tab,size);
    swapTab(tab,1,2);
    showTab(tab,size);

    int **tab2=multiDimensionOGABOOGA();
    showTab(tab2,5);
    tab2[3][0]=9;
    showTab(tab2,5);

    int *tab3=new int[10];
    generateValues(tab3,10,20,40);
    showTab(tab3,10);

    char* zdanie="Ala ma kota";
    cout<<zdanie<<endl;
    char** slowa=returnWords(zdanie,11);
    cout<<slowa[1]<<endl;

    //showTab(slowa,3);




    return 0;
}