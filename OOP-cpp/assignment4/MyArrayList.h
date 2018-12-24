#ifndef MYARRAYLIST_H
#define MYARRAYLIST_H
#include <iostream>
#include <string>

using namespace std;

template < typename T >
class MyArrayList {

    private:
        const static int DEFAULT_CAPACITY = 10;
        T * mass;
        int capacity;
        int size;

    public:
        
        MyArrayList(){
            mass = new T[DEFAULT_CAPACITY];
            capacity = DEFAULT_CAPACITY;
            size = 0;
        };
        MyArrayList(int x){
            capacity = x;
            mass = new T[capacity];
            size =0;
        };
        void resize(){
            capacity = capacity * 2;
        };
        bool addElement(T const &x){
            if(size < capacity)
            {
                mass[size++] = x;
                return true;
            }
            else{
                resize();
                return false;
            }
        };
        void printIt(){
            cout<<"{ ";
            for(int i =0; i<size; i++){
                cout<<mass[i];
                if(i+1 != size){
                    cout<<", ";
                }
             }
            cout<<" }"<<endl;
            cout<<""<<endl;
        };

};
#endif