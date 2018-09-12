//201404377_진승언
package interpreter;

import java.util.HashMap;
import java.util.Scanner;

import parser.ast.BinaryOpNode;
import parser.ast.BooleanNode;
import parser.ast.FunctionNode;
import parser.ast.IdNode;
import parser.ast.IntNode;
import parser.ast.ListNode;
import parser.ast.Node;
import parser.ast.QuoteNode;
import parser.parse.CuteParser;
import parser.parse.NodePrinter;

public class CuteInterpreter {

	static HashMap<String, Node> map = new HashMap<String, Node>(); // 추가

	private void errorLog(String err) {
		System.out.println(err);
	}

	public Node runExpr(Node rootExpr) { // 초기 노드 파싱
		if (rootExpr == null)
			return null;
		if (rootExpr instanceof IdNode) {
			////////////////////////////////////////////////// (item2) 디파인한것을 출력해줌 ex)
			////////////////////////////////////////////////// (define a 3) 이라했으면 a를 입력하면
			////////////////////////////////////////////////// a가아니라 3이나오게함
			if (lookupTable(rootExpr.toString()) != null) { // 테이블에 해당 정의가 있을 경우
				return lookupTable(rootExpr.toString());
			}
			///////////////////////////////////////////////////////
			else { // 그냥 Id반환
				return rootExpr;
			}
		} else if (rootExpr instanceof IntNode)
			return rootExpr;

		else if (rootExpr instanceof BooleanNode)
			return rootExpr;
		else if (rootExpr instanceof ListNode)
			return runList((ListNode) rootExpr); // 리스트인경우 Node runList(ListNode list)로 가짐 <이외 에는 그냥 노드반환>
		else
			errorLog("run Expr error");
		return null;
	}

	private Node runList(ListNode list) { // 리스트일 경우의 조작
		if (list.equals(ListNode.EMPTYLIST))
			return list;

		if (list.car() instanceof FunctionNode) { // FunctionNode인 경우 (car, cdr , NULLQ등)
			return runFunction((FunctionNode) list.car(), list.cdr());
		}
		if (list.car() instanceof BinaryOpNode) { // BinaryOpNode 사칙연산인 경우
			return runBinary(list);
		}
		return list; // 특수없으면 그냥 반환
	}

	void insertTable(String id, Node value) { // 테이블 함수추가
		try {
			if (value instanceof ListNode) { // ( define a ( < 1 2 ) ) 같이 바로 리스트가 오고 이것은 계산되서 #T로 저장되야함
				value = runList((ListNode) value); // 리스트의 결과값을 받아옴(특수문자 없으면 그냥반환)
			}

			if (value instanceof ListNode) { // 각각의 형에 맞게 값을 테이블에 추가해줌
				map.put(id, (IntNode) value);
				System.out.println("..ListNode값이 테이블에 추가되었습니다");
			} else if (value instanceof QuoteNode) {
				map.put(id, (QuoteNode) value);
				System.out.println("..QuoteNode값이 테이블에 추가되었습니다");
			} else if (value instanceof IntNode) {
				map.put(id, (IntNode) value);
				System.out.println("..IntNode값이 테이블에 추가되었습니다");
			} else if (value instanceof BooleanNode) {
				map.put(id, (BooleanNode) value);
				System.out.println("..BooleanNode값이 테이블에 추가되었습니다");
			} else if (value instanceof IdNode) {
				map.put(id, (IdNode) value);
				System.out.println("..IdNode값이 테이블에 추가되었습니다");
			}
		} catch (Exception e) {
			errorLog("run Expr error");
		}

	}

	Node lookupTable(String id) { // 테이블에서 데이터 꺼내오는 함수 추가
		if (map.containsKey(id)) {
			Node node = map.get(id);
			return node; // (define a 3)하면 ( 3 ) 이렇게 괄호 붙어서 반환
		} else
			return null; // 업을 경우 null반환
	}

	private Node runFunction(FunctionNode operator, ListNode operand) {
		switch (operator.value) { // CAR, CDR, CONS등에 대한 동작 구현
		case CAR:
			//////////////////////////////////////////////////////////////////////////////////////// (item2)

			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null ) { // IdNode일 떄는 define한것 일 수도 있으므로 검사해준다
				Node result = ((QuoteNode) lookupTable(((IdNode) operand.car()).toString())).nodeInside(); // `다음 리스트반환
																											// 괄호없이
																											// 반환되므로
																											// runQuote사용불가
				return ((ListNode) result).car(); // 그 리스트의 첫번쨰 값 반환
			}
			/////////////////////////////////////////////////////////////////////////

			else if (operand.car() instanceof QuoteNode) { // 먼저 car다음에 오는 노드가 `인지 검사
				ListNode result = (ListNode) runQuote(operand);
				return result.car();
			} else {
				errorLog("run Expr error");
			}
			return null;

		case CDR:
			////////////////////////////////////////////////// (item2)

			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null) { // IdNode일 떄는 define한것 일 수도 있으므로 검사해준다
				Node result = ((QuoteNode) lookupTable(((IdNode) operand.car()).toString())).nodeInside(); // `다음 리스트반환
																											// 괄호없이
																											// 반환되므로
																											// runQuote사용불가
				return ((ListNode) result).cdr(); // 그 리스트의 첫번쨰 값 반환
			}
			///////////////////////////////////////////////////////////

			else if (operand.car() instanceof QuoteNode) {
				ListNode result = (ListNode) runQuote(operand);
				return result.cdr();
			} else {
				errorLog("run Expr error");
			}
			return null;
		case CONS: // ex ` (234)
			Node tmp = operand.car();
			Node tmp2 = operand.cdr().car();
			if (operand.car() instanceof IdNode && lookupTable(((IdNode) tmp).toString()) != null) { // 첫 노드가 테이블갑
				tmp = lookupTable(((IdNode) tmp).toString()); // `(234)
			}
			if (operand.cdr().car() instanceof IdNode && lookupTable(((IdNode) tmp2).toString()) != null) { // 두번째 노드가
																											// 테이블값
				tmp2 = lookupTable(((IdNode) tmp2).toString());
			}
			if (tmp instanceof QuoteNode) { // 처음꼐 `인 경우 (리스트가 오는 경우)
				Node result = ListNode.cons(((QuoteNode) tmp).nodeInside(), (ListNode) ((QuoteNode) tmp2).nodeInside());
				return result;
			} else {
				Node result = ListNode.cons(tmp, (ListNode) ((QuoteNode) tmp2).nodeInside());
				return result;
			}

		case NULL_Q:
			/////////////////////////////////////////////////////// // 추가(item2) define문인지
			/////////////////////////////////////////////////////// 확인
			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null) { // null_q는
																												// 리스트가오는
																												// 경우인데
																												// 이아이디가
																												// 오는경우는
																												// define문이
																												// 오는경우임
				Node temp = ((QuoteNode) lookupTable(((IdNode) operand.car()).toString())).nodeInside(); // 리스트를 가져옴
				if (((ListNode) temp).car() == null) {
					BooleanNode result = BooleanNode.TRUE_NODE;
					return result;
				} else {
					BooleanNode result = BooleanNode.FALSE_NODE;
					return result;
				}
			}
			///////////////////////////////////////////////////////////////////////////
			else if (((ListNode) runQuote(operand)).car() == null) {
				BooleanNode result = BooleanNode.TRUE_NODE;
				return result;
			} else {
				BooleanNode result = BooleanNode.FALSE_NODE;
				return result;
			}
		case COND:
			try {
				ListNode listNode = operand;
				while (listNode != ListNode.EMPTYLIST) {
					/////////////////////////////////////////////////////////////////// (item2)
					BooleanNode node;
					if (runExpr(((ListNode) listNode.car()).car()) instanceof IdNode
							&& lookupTable(((IdNode) ((ListNode) listNode.car()).car()).toString()) != null) { // if문으로 #T #F로 정의된 테이블 값이 올 경우
						node = (BooleanNode) lookupTable(((IdNode) ((ListNode) listNode.car()).car()).toString());
					}
					///////////////////////////////////////////////////////////////////////////////

					else { // 일반적인 #T #F가 왔을 경우
						node = (BooleanNode) runExpr(((ListNode) listNode.car()).car());
					}

					if (node == (BooleanNode.TRUE_NODE)) {

						/////////////////////////////////////////////////////////////////////// (item2)

						if (((ListNode) listNode.car()).cdr().car() instanceof IdNode && lookupTable(
								((IdNode) (((ListNode) listNode.car()).cdr().car())).toString()) != null) { // 테이블 값일 경우
							Node result = lookupTable(((IdNode) (((ListNode) listNode.car()).cdr().car())).toString());
							if (result instanceof QuoteNode) { // 정의문이 Quote로 되있는 경우
								return result; // (수정해줬음)
							} else { // 쿼트문이 아닐경우
								return result; // 위와 합쳐도되나 혹시모르니 그대로냅둠(마무리)
							}

						}
						//////////////////////////////////////////////////////////////////////////////
						else {
							return ((ListNode) listNode.car()).cdr().car();
						}
					} else {
						listNode = operand.cdr(); // 다음 리스트로 부른다
					}
				}
			} catch (Exception e) {
				errorLog("run Expr error");
			}

		case NOT:
			/////////////////////////////////////////////////////////////////////////////// (item2)
			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null) { // 다음으로 테이블 값이 올 경우 
				Node temp = lookupTable(((IdNode) operand.car()).toString()); // define a #T 인 경우 ( #T ) 값가져옴]
				if (temp instanceof BooleanNode) {
					if (temp == BooleanNode.TRUE_NODE)
						return BooleanNode.FALSE_NODE;
					else
						return BooleanNode.TRUE_NODE;
				}
			}
			////////////////////////////////////////////////////////////////////////////
			else {
				if (operand.car() instanceof BooleanNode) {
					if (operand.car() == BooleanNode.TRUE_NODE)
						return BooleanNode.FALSE_NODE;
					else
						return BooleanNode.TRUE_NODE;
				} else if (((ListNode) operand.car()).car() instanceof FunctionNode) {
					if (runFunction((FunctionNode) ((ListNode) operand.car()).car(),
							((ListNode) operand.car()).cdr()) == BooleanNode.TRUE_NODE)
						return BooleanNode.FALSE_NODE;
					else
						return BooleanNode.TRUE_NODE;
				} else if (((ListNode) operand.car()).car() instanceof BinaryOpNode) {
					if (runBinary((ListNode) operand.car()) == BooleanNode.TRUE_NODE)
						return BooleanNode.FALSE_NODE;
					else
						return BooleanNode.TRUE_NODE;
				} else {
					errorLog("run Expr error");
					return null;
				}
			}

		case ATOM_Q:
			////////////////////////////////////////////////// (item2)
			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null) { // define문
																												// 일 경우
				Node temp = ((QuoteNode) lookupTable(((IdNode) operand.car()).toString())).nodeInside(); // 값 추출
				if (temp instanceof IdNode || temp instanceof IntNode) { // 리스트 아님
					return BooleanNode.TRUE_NODE;
				} else if (temp instanceof ListNode) // 리스트임
					return BooleanNode.FALSE_NODE;
				

			} else {
				//////////////////////////////////////////////////

				if(operand.car() instanceof QuoteNode) { // `없이  단순 값이왔을떄 ex) 3, A
				if (runQuote(operand) instanceof IdNode || runQuote(operand) instanceof IntNode) { // 리스트 아님
					return BooleanNode.TRUE_NODE;
				} else if ((ListNode) runQuote(operand) instanceof ListNode) // 리스트임
					return BooleanNode.FALSE_NODE;
				else {
					errorLog("run Expr error");
					return null;
				}
			}
			else {
				return BooleanNode.TRUE_NODE;
			}
			}

		case EQ_Q:
			if (operand.car() instanceof IdNode) { // 첫노드가 id일 떄 (
				if (lookupTable(operand.car().toString()) != null) { // 첫 노드가 테이블값일 때
					if (((ListNode) operand.cdr()).car() instanceof IdNode) { // 두번쨰가 id일때
						if (lookupTable((((ListNode) operand.cdr()).car()).toString()) != null) { // 두번쨰가 테이블값일 때
							if (lookupTable(operand.car().toString()) instanceof QuoteNode) { // 테이블값이 quote일때
								if (lookupTable(((operand.cdr()).car()).toString()) instanceof QuoteNode) { // 뒤의 테이블값이
																											// quote일 때
									ListNode temp = (ListNode) ((QuoteNode) lookupTable(operand.car().toString()))
											.nodeInside();
									ListNode temp2 = (ListNode) ((QuoteNode) lookupTable(
											(((ListNode) operand.cdr()).car()).toString())).nodeInside();

									while (temp.car() != null && temp.cdr() != null && temp2.car() != null
											&& temp2.cdr() != null) { // 리스트끼리의 값 비교
										if (temp.car().toString().equals(temp2.car().toString())) {
											temp = temp.cdr();
											temp2 = temp2.cdr();
											continue;
										} else
											return BooleanNode.FALSE_NODE; // 하나라도 다르면 FALSE
									}
									if (temp.car() != null || temp.cdr() != null || temp2.car() != null
											|| temp2.cdr() != null)
										return BooleanNode.FALSE_NODE; // 개수비교
									else
										return BooleanNode.TRUE_NODE;
								} else { // 앞 테이블 값은 쿼트인데 뒤는 쿼트아닐떄 false
									return BooleanNode.FALSE_NODE;
								}
							} else { // 처음 테이블값이 쿼트가아니라 한개일때
								if (((ListNode) operand.cdr()).car() instanceof ListNode) { // 뒤에거는 리스트일경우 false
									return BooleanNode.FALSE_NODE;
								} else { // 처음 테이블값 두번쨰 테이블값 모두 단수일 때 (비교해줌)
									if (lookupTable(operand.car().toString()).toString().equals(
											lookupTable((((ListNode) operand.cdr()).car()).toString()).toString()))
										return BooleanNode.TRUE_NODE; // 앞에 변수와 뒤에 변수에 대한 EQ_Q
									else
										return BooleanNode.FALSE_NODE;
								}
							}
						} else { // 두번째가 테이블값이 아니라 id 일 때
							if ((lookupTable(operand.car().toString())).toString()
									.equals((operand.cdr().car()).toString()))
								return BooleanNode.TRUE_NODE;
							else
								return BooleanNode.FALSE_NODE;
						}
					} else { // 두번째 노드가 Int일때
						if ((lookupTable(operand.car().toString())).toString().equals((operand.cdr().car()).toString()))
							return BooleanNode.TRUE_NODE;
						else
							return BooleanNode.FALSE_NODE;
					}
				} else { // 첫번째 값이 테이블값이 아니라 id일때
					if (operand.car().toString().equals((operand.cdr().car()).toString()))
						return BooleanNode.TRUE_NODE;
					else
						return BooleanNode.FALSE_NODE;
				}
			} else if (operand.car() instanceof IntNode) { // 첫번째 값이 Int일때
				if (((ListNode) operand.cdr()).car() instanceof IdNode) { // 두번째 값이 id일때
					if (lookupTable((((ListNode) operand.cdr()).car()).toString()) != null) {
						if (lookupTable((((ListNode) operand.cdr()).car()).toString()) instanceof ListNode) {
							return BooleanNode.FALSE_NODE;
						} else if (operand.car().toString()
								.equals(lookupTable((((ListNode) operand.cdr()).car()).toString()).toString()))
							return BooleanNode.TRUE_NODE;
						else
							return BooleanNode.FALSE_NODE;
					}
				} else { // 두번째 값이 int일떄
					if (operand.car().toString().equals((operand.cdr().car()).toString()))
						return BooleanNode.TRUE_NODE;
					else
						return BooleanNode.FALSE_NODE;
				}
			} else if (runQuote(operand) instanceof IntNode || runQuote(operand) instanceof IdNode) { // Int,Id 일때
				if (runQuote(operand).toString().equals(runQuote(operand.cdr()).toString()))
					return BooleanNode.TRUE_NODE;
				else
					return BooleanNode.FALSE_NODE;
			} else { // 리스트일때
				ListNode temp = (ListNode) runQuote((ListNode) operand);
				ListNode temp2 = (ListNode) runQuote((ListNode) operand.cdr());

				while (temp.car() != null && temp.cdr() != null && temp2.car() != null && temp2.cdr() != null) { // 리스트값들
																													// 비교
					if (temp.car().toString().equals(temp2.car().toString())) {
						temp = temp.cdr();
						temp2 = temp2.cdr();
						continue;
					} else
						return BooleanNode.FALSE_NODE;
				}
				if (temp.car() != null || temp.cdr() != null || temp2.car() != null || temp2.cdr() != null)
					return BooleanNode.FALSE_NODE; // 리스트 개수비교
				else
					return BooleanNode.TRUE_NODE;
			}

		case DEFINE:
			Node id_node = operand.car(); // 정의할 단어
			String id = id_node.toString();
			Node list = operand.cdr().car(); // 정의될 단어의 내용
			insertTable(id, list); // 테이블에 추가

		default:
			break;
		}
		return null;

	}

	private Node runBinary(ListNode list) {
		BinaryOpNode operator = (BinaryOpNode) list.car(); // 구현과정에서 필요한 변수 및 함수 작업 가능

		Node node1 = list.cdr().car(); // 처음변수
		Node node2 = list.cdr().cdr().car(); // 두번째변수

		IntNode num1 = (IntNode) runExpr(node1); // runExpr했을때 리스트 노드이고 리스트의 첫 노드가(car) +,- 등 일 경우 여기로 다시옴 (재귀)
		IntNode num2 = (IntNode) runExpr(node2);

		switch (operator.value) { // +,-,/ 등에 대한 바이너리 연산 동작 구현

		case PLUS:
			try {
				IntNode result = new IntNode(num1.value + num2.value + "");
				return result;
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		case MINUS:
			try {

				IntNode result = new IntNode(num1.value - num2.value + "");
				return result;
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		case DIV:
			try {
				IntNode result = new IntNode(num1.value / num2.value + "");
				return result;
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		case TIMES:
			try {
				IntNode result = new IntNode(num1.value * num2.value + "");
				return result;
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		case LT:
			if (num1.value < num2.value) {
				BooleanNode result = BooleanNode.TRUE_NODE;
				return result;
			} else if (num1.value > num2.value) {
				BooleanNode result = BooleanNode.FALSE_NODE;
				return result;
			} else {
				errorLog("run Expr error");
			}

		case GT:
			if (num1.value > num2.value) {
				BooleanNode result = BooleanNode.TRUE_NODE;
				return result;
			} else if (num1.value < num2.value) {
				BooleanNode result = BooleanNode.FALSE_NODE;
				return result;
			} else {
				errorLog("run Expr error");
			}

		case EQ:

			try {
				if (num1.value.equals(num2.value)) {
					BooleanNode result = BooleanNode.TRUE_NODE;
					return result;
				} else {
					BooleanNode result = BooleanNode.FALSE_NODE;
					return result;
				}
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		default:
			break;
		}
		return null;
	}

	private Node runQuote(ListNode node) {
		return ((QuoteNode) node.car()).nodeInside();
	}

	public static void main(String[] args) {
		while (true) {

			System.out.print(">"); // 추가(8주차)
			Scanner scanner = new Scanner(System.in); // 추가(8주차)
			String str = scanner.nextLine(); // 추가(8주차)
			CuteParser cuteParser = new CuteParser(str);
			Node parseTree = cuteParser.parseExpr();
			CuteInterpreter i = new CuteInterpreter();
			Node resultNode = i.runExpr(parseTree);
			System.out.print(".. "); // 추가(8주차)
			NodePrinter.getPrinter(System.out).prettyPrint(resultNode);
			System.out.println(); // 추가(8주차)

		}

	}
}
