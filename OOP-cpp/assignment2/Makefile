assignment2: main.o person.o footballer.o mathTeacher.o
	g++ -o assignment2 main.o person.o footballer.o mathTeacher.o 

main.o: main.cpp
	g++ -c -o main.o main.cpp

person.o: person.cpp
	g++ -c -o person.o person.cpp

footballer.o: footballer.cpp
	g++ -c -o footballer.o footballer.cpp

mathTeacher.o: mathTeacher.cpp
	g++ -c -o mathTeacher.o mathTeacher.cpp

clean:
	rm *.o assignment2

