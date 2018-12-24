#include <iostream>

class Singleton{
    private:
        static Singleton* instance;

        Singleton();

    public:
        static Singleton* getInstance();

};

Singleton* Singleton::instance = 0;

Singleton* Singleton::getInstance()
{
    if(instance == 0){
        instance = new Singleton();
    }
    return instance;
}

Singleton::Singleton(){}

int main(){
    Singleton* s = Singleton::getInstance();
    Singleton* r = Singleton.getInstance();

    std::cout<<s<<std::endl;
    std::cout<<r<<std::endl;
    
}