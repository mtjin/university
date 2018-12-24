main: main.o apple.o orange.o
	g++ -o main main.o apple.o orange.o

main.o: main.cpp
	g++ -c -o main.o main.cpp

apple.o: apple.cpp
	g++ -c -o apple.o apple.cpp

orange.o: orange.cpp
	g++ -c -o orange.o orange.cpp

clean:
	rm *.o main
