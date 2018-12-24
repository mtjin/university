assignment3: main.o ShapePattern.o RectanglePattern.o TrianglePattern.o
	g++ -o assignment3 main.o ShapePattern.o RectanglePattern.o TrianglePattern.o 

main.o: main.cpp
	g++ -c -o main.o main.cpp

ShapePattern.o: ShapePattern.cpp
	g++ -c -o ShapePattern.o ShapePattern.cpp

RectanglePattern.o: RectanglePattern.cpp
	g++ -c -o RectanglePattern.o RectanglePattern.cpp

TrianglePattern.o: TrianglePattern.cpp
	g++ -c -o TrianglePattern.o TrianglePattern.cpp

clean:
	rm *.o assignment3

