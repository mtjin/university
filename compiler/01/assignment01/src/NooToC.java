import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by eschough on 2019-09-02.
 */
public class NooToC {
    StringBuffer buffer = new StringBuffer(); //c언어 변환 결과값 담을 버퍼

    public NooToC(FileWriter fw, String nooPgm) throws IOException {
        //초깃값 append
        append("#include  <stdio.h>");
        append("int main() {");
        append("int r, t1, t2, t3;");
        //명령어의 단위인 ' 단위로 토큰 받아오게끔 한다.
        StringTokenizer st = new StringTokenizer(nooPgm, "\'");
        //if문 내용하고 중괄호 시작할 차례인지 {
        boolean isIfStartState = false;
        //if문 중괄호 닫아야할 차례인지 }
        boolean isIfEndState = false;
        //else문  중괄호 닫아야할 차례인지 }
        boolean isElseEndState = false;
        //토큰하나씩 받아오기
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            //콤마 두개("") 작업해야할 횟수
            int twoCount = 0;
            if (str.length() == 5) { //콤마 다섯개일때
                //if문 시작해야함
                isIfStartState = true;
            }
            else if (str.length() == 4) { //콤마 네개일때
                //할게없는데...?
            } else if (str.length() == 1 || isIfStartState) { //콤마 한개이고 if문 시작해야하는 코드일때 (이 안에서 콤마 1개 2개 3개인 작업 수행)
                boolean isTwo = false;
                while (true) {
                    if (!isIfStartState || isTwo) { //if문일때는 토큰을 위에서 받아왔으므로 또 받을 필요가없음
                        str = st.nextToken();
                    }
                    if (str.length() == 2) { //콤마 두개인 경우(연속으로 올 수 있음 카운트해야함)
                        //더하기 1을 해야하는 수식 몇번해야하는지 카운트
                        twoCount++;
                        //두번쨰 콤마 작업완료
                        isTwo = true;
                    } else if (str.length() == 3) { //콤마 세개인 경우
                        //콤마 세개일떄의 출력문 append
                        append("r = 0;");
                        //TODO : ""
                        for (int i = 0; i < twoCount; i++) { //두번쨰 콤마 카운트만큼 출력문 append
                            append("t1 = r;");
                            append("r =  t1 + 1;");
                        }
                        break;
                    }
                }
                if (isIfEndState) { //if문 끝나야하는 차례인 경우
                    //해당 출력문들 append 및 다음 차례 설정(boolean값)
                    append("printf(\"%d\", r);");
                    append("}");
                    append("else{");
                    isIfEndState = false;
                    isElseEndState = true;
                } else if (isElseEndState) { //else문 끝나야하는 차례인 경우
                    //해당 출력문들 append 및 다음 차례 설정(boolean값)
                    append("printf(\"%d\", r);");
                    append("}");
                    isElseEndState = false;
                } else if (isIfStartState) { //if문 시작할 차례인경우
                    //해당 출력문들 append 및 다음 차례 설정(boolean값)
                    append("t1 = r;");
                    append("if (t1 != 0)  {");
                    isIfStartState = false;
                    isIfEndState = true;
                } else { //콤마 한개에대한 출력문 append(콤마 세개 두개의 작업이 다 끝난 후 콤마 한개에 대한 작업을 수행함)
                    append("printf(\"%d\", r);");
                }
            } else if (str.length() == 2) { //콤마 한개가 없고 두개가 나온 경우 (위에   else if (str.length() == 1 || isIfStartState) { 와 동일한 구조를 갖는다. ) ,  isIfStartState가 없다는 점과 출력문 print이 없다는 점뺴곤 동일하다.
                //위와 동일하므로 주석은 생략하겠습니다.
                twoCount++;
                while (true) {
                    str = st.nextToken();
                    if (str.length() == 2) {
                        //TODO: ""
                        twoCount++; //더하기 1을 해야하는 수식 몇번해야하는지 카운트
                    } else if (str.length() == 3) {
                        //TODO: """
                        append("r = 0;");
                        //TODO : ""
                        for (int i = 0; i < twoCount; i++) {
                            append("t1 = r;");
                            append("r =  t1 + 1;");
                        }
                        break;
                    }
                }
                if (isIfEndState) {
                    append("}");
                    append("else{");
                    isIfEndState = false;
                    isElseEndState = true;
                } else if (isElseEndState) {
                    append("}");
                    isElseEndState = false;
                } else if (isIfStartState) {
                    //TODO: """""
                    append("if (t1 != 0)  {");
                    isIfStartState = false;
                    isIfEndState = true; //if문 }작성
                }
            }
        }

        //마무리 append
        append("return 1;");
        append("}");
        // System.out.println(buffer);
        fw.write(buffer.toString());
        fw.flush();
    }

    //StringBuffer에 줄바꿈과 함께 문자열 추ㅏ
    public void append(String str) {
        buffer.append(str + "\n");
    }

}
