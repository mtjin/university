//201404377_진승언
package lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Stream;

public class ScannerMain {
    public static final void main(String... args) throws Exception {
        ClassLoader cloader = ScannerMain.class.getClassLoader();
        File file = new File(cloader.getResource("as03.txt").getFile());   //파일 읽어옴
        testTokenStream(file);
    }
    
    // use tokens as a Stream 
    private static void testTokenStream(File file) throws FileNotFoundException {	
        Stream<Token> tokens = Scanner.stream(file);  //스트림은 리스트랑 비슷한데 이를 토큰들을 한번에 다받아서 규칙대로 뽑아낼 수 있는듯
        tokens.map(ScannerMain::toString).forEach(System.out::println);  //map은 규칙대로 나누는걸의미하고 foreach는 반복문을의미
    }    
    
    private static String toString(Token token) {
        return String.format("%-3s: %s", token.type().name(), token.lexme());
    }
    
}
