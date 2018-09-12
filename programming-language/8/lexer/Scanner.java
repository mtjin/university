//201404377_진승언
package lexer;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Scanner {
    // return tokens as an Iterator
    public static Iterator<Token> scan(String str) throws FileNotFoundException {   //수정했음 (8주차)
        ScanContext context = new ScanContext(str);    
        return new TokenIterator(context);              
    }

    // return tokens as a Stream 
    public static Stream<Token> stream(String str) throws FileNotFoundException {  //수정했음 (8주차)
        Iterator<Token> tokens = scan(str);                              //토큰을 위에 함수로 가져왔음 
        return StreamSupport.stream(                                      //토큰을 스트림으로 가져옴 
                Spliterators.spliteratorUnknownSize(tokens, Spliterator.ORDERED), false);
    }
}