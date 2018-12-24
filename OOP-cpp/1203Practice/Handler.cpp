#include <iostream>

using namespace std;

class Handler
{
    public:
        Handler(Handler *pHandle) : pHandler(pHandler){}
        ~Handler(){if(pHandler) delete pHandler;}

    public:
        virtual void handleRequest(int i){
            if(pHandler != nNULL)
            pHandler->handleRequest(i);
        }
    private:
    Handler *pHandler;
};

class ConcreateHandler: public Handler{
    public:
        ConcreateHandler1(Handler *pHandle) : Handler(pHandle){}
    
    public:
        void handleRequest(int i ) override{
            if(i%2 == 0)
                cout<<"even number"<<endl;
            else
                Handler::handleRequest(i);
        }
}