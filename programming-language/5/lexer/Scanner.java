//201404377_진승언
package lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Scanner {
    // return tokens as an Iterator
    public static Iterator<Token> scan(File file) throws FileNotFoundException {      //토큰을 차례대로가져옴
        ScanContext context = new ScanContext(file);    //파일을 읽어옴
        return new TokenIterator(context);              
    }

    // return tokens as a Stream 
    public static Stream<Token> stream(File file) throws FileNotFoundException {
        Iterator<Token> tokens = scan(file);                              //토큰을 위에 함수로 가져왔음 
        return StreamSupport.stream(                                      //토큰을 스트림으로 가져옴 
                Spliterators.spliteratorUnknownSize(tokens, Spliterator.ORDERED), false);
    }
}