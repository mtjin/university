/*
.class public JvmTest
.super java/lang/Object
; strandard initializer
 */
public class JvmTest {

	/*
	 .method public <init>()V 
	 aload_0 
	 invokenonvirtual java/lang/Object/<init>()V 
	 return 
	.end method
	 */
	public JvmTest() {

	}

	/*
	.method public static sum(I)I
	.limit stack 32 
	.limit locals 32 
	iconst_0
	istore_1
	iconst_1
	istore_2
	goto  22
	17:iload_1
	iload_2
	iadd
	istore_1
	iinc          2 1
	22: iload_2
	iload_0
	if_icmple     17
	iload_1
	ireturn
	.end method
	 */
	public static int sum(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		return sum;
	}

	/*
	 .method public static main([Ljava/lang/String;)V 
	.limit stack 32 
	.limit locals 32 
	getstatic java/lang/System/out Ljava/io/PrintStream; 
	bipush        100
	invokestatic  JvmTest/sum(I)I
	invokevirtual java/io/PrintStream/println(I)V
	return
	.end method
	 */
	public static void main(String[] args) {
		System.out.println(sum(100));
	}
}
