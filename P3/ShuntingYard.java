import java.util.ArrayDeque;
import java.util.LinkedList;

public class ShuntingYard {

	public static int makePostfix(ArrayDeque<Character> queue) {
		LinkedList<Integer> numberStack = new LinkedList<>();
		int aux;
		int op1;
		int op2;
		while (!queue.isEmpty()) {
			Character ch = queue.removeFirst();
			if (isNumeric(ch)) {
				numberStack.addFirst(Character.getNumericValue(ch));
				continue;
			}
			if (isOperator(ch)) {
				if (numberStack.isEmpty()) {
					System.out.println("expresia postfixata este gresita");
					return -1;
				} else {
					op1 = numberStack.pop();
				}
				if (numberStack.isEmpty()) {
					System.out.println("expresia postfixata este gresita");
					return -1;
				} else {
					op2 = numberStack.pop();
				}
				aux = math(op1, op2, ch);
				numberStack.addFirst(aux);
			}
		}
		int result = numberStack.pop();
		if (!numberStack.isEmpty()) {
			System.out.println("expresia postfixata este gresita");
			return -1;
		}
		return result;
	}

	public static ArrayDeque<Character> postfix(String str) {
		ArrayDeque<Character> queue = new ArrayDeque<>();
		LinkedList<Character> stack = new LinkedList<>();
		for (char ch : str.toCharArray()) {
			if (isNumeric(ch)) {
				queue.add(ch);
			}
			if (isOperator(ch)) {
				while (!stack.isEmpty() && isOperator(stack.peek()) && stack.peek() != '('
						&& (precedence(ch) < precedence(stack.peek())
								|| precedence(ch) == precedence(stack.peek()) && leftPrecedence(stack.peek()))) {
					// 1.3.1.1
					queue.add(stack.pop());
				}
				// 1.3.2
				stack.addFirst(ch);
			}
			// 1.4
			if (ch == '(') {
				stack.addFirst(ch);
			}
			// 1.5
			if (ch == ')') {
				// 1.5.1
				while (stack.peek() != '(') {
					queue.add(stack.pop());
				}
				// 1.5.2
				if (stack.isEmpty()) {
					System.out.println("expresia avea paranteze gresite");
					return queue;
				}
				// 1.5.3
				stack.pop();
			}
		}
		while (!stack.isEmpty()) {
			if (stack.peek() == '(') {
				System.out.println("expresia avea paranteze gresite");
				return queue;
			}
			queue.add(stack.pop());
		}
		for (char ch : queue) {
			System.out.print(ch + "");
		}
		return queue;
	}

	private static boolean isOperator(char ch) {
		Character[] operators = { '+', '-', '*', '/', '^' };
		for (char op : operators) {
			if (ch == op) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNumeric(Character str) {
		return Character.isDigit(str);
	}

	private static boolean leftPrecedence(char ch) {
		Character[] operators = { '+', '-', '*', '/'};
		for (char op : operators) {
			if (ch == op) {
				return true;
			}
		}
		return false;
	}

	private static int precedence(char ch) {
		if(ch == '+' || ch == '-') {
			return 11;
		} else if(ch == '*' || ch == '/') {
			return 12;
		} else if(ch == '^') {
			return 13;
		}
		return -1;
	}

	private static int math(int a, int b, char ch) {
		if(ch == '+') {
			return a + b;
		} else if( ch == '-') {
			return b - a;
		} else if(ch == '*') {
			return a * b;
		} else if(ch == '/') {
			return b / a;
		} else if(ch == '^') {
			return (int) Math.pow(b,a);
		} 
		return -1;
	}

	public static void main(String[] args) {
		String str = "3+(2+1)*2^3^2-8/(5-1*2/2)";
		ArrayDeque<Character> postfixed = postfix(str);
		int res = makePostfix(postfixed);
		System.out.println();
		System.out.println("---------- ");
		System.out.println(res);
	}

}