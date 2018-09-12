//201404377_Áø½Â¾ð
package parser.parse;

import java.io.PrintStream;
import java.util.StringTokenizer;

import parser.ast.*;

public class NodePrinter {
	private PrintStream ps;

	public static NodePrinter getPrinter(PrintStream ps) {
		return new NodePrinter(ps);
	}

	private NodePrinter(PrintStream ps) {
		this.ps = ps;
	}

	private void printNode(ListNode listNode) {
		if (listNode.equals(ListNode.EMPTYLIST)) {
			ps.print("( )");
			return;
		}
		if (listNode.equals(ListNode.ENDLIST)) {
			return;
		}
		printNode(listNode.car());
		if (listNode.cdr().equals(ListNode.EMPTYLIST)) {
			ps.print(" ");
		}
		printNode(listNode.cdr());
	}

	private void printNode(QuoteNode quoteNode) {
		if (quoteNode.nodeInside() == null)
			return;
		ps.print(" '");
		printNode(quoteNode.nodeInside());
	}

	private void printNode(Node node) {
		if (node == null)
			return;
		if (node instanceof ListNode) {
			ps.print("(");
			printNode((ListNode) node);
			ps.print(" )");
		} else if (node instanceof QuoteNode) {
			printNode((QuoteNode) node);
		} else if (node instanceof BooleanNode) {
			if (node == BooleanNode.FALSE_NODE)
				ps.print("#F");
			else
				ps.print("#T");
		}
		/////////////////////(item2 Ãß°¡)
		else if (node instanceof FunctionNode) {
			ps.print((node.toString().toLowerCase()));
		} 
		/////////////////
		else {
			String temp = node.toString();
			StringTokenizer st = new StringTokenizer(temp, " ");
			st.nextToken();
			ps.print(" " + st.nextToken());
		}
	}

	public void prettyPrint(Node node) {
		printNode(node);
	}

}