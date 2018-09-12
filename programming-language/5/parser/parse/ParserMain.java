//201404377_진승언
package parser.parse;

import java.io.File;

public class ParserMain {

	public static final void main(String... args) throws Exception {    
		ClassLoader cloader = ParserMain.class.getClassLoader();    
		File file = new File(cloader.getResource("parser/as05.txt").getFile());  
		//file read는 편한 대로 작성(위의 코드는 예시)       
		CuteParser cuteParser = new CuteParser(file);     
		NodePrinter.getPrinter(System.out).prettyPrint(cuteParser.parseExpr());              
		} 
	

}
