package com.niuer.ljj.������;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * ������,��׺���ʽת��׺���ʽ
 * 
 * @author xu
 *
 */
public class xx {

	/**
	 * ��׺���ʽת��׺���ʽ
	 * 
	 * @param infix
	 */
	public static Queue<String> getPostfix(Queue<String> infix) {
		System.out.print(infix);

		/** ��׺���ʽջ */
		Queue<String> postfix = new LinkedList<>();
		//System.out.println(infix);

		/** �ݴ��������ջ */
		Stack<String> operateSymbol = new Stack<>();

		while (!infix.isEmpty()) {
			// ����"("ֱ�ӽ�"("ѹջ,"("���ȼ����
			if (infix.peek() == "(") {
				operateSymbol.push(infix.poll());
			} else if (infix.peek() == ")") {
				// ����")"�������ĵ�һ��"("֮ǰ��Ԫ��ȫ���ŵ���׺���ʽ������,Ȼ���ٵ���"("
				while (operateSymbol.peek() != "(" && !operateSymbol.empty()) {
					postfix.add(operateSymbol.pop());
				}
				operateSymbol.pop();
			} else if (infix.peek() == "+" || infix.peek() == "-") {
				// ��ջΪ�ջ�ջ��Ԫ��Ϊ"(",��ֱ��ѹջ
				// ��ջ��Ϊ��,���ж�ջ��Ԫ�ص����ȼ�,�����ڻ����"+",���䵯��ֱ��ջ��Ԫ�����ȼ�С��"+"
				// Ȼ��"+"ѹջ
				if (operateSymbol.empty() || operateSymbol.peek() == "(") {
					operateSymbol.push(infix.poll());
				} else {
					while (!operateSymbol.empty()) {
						if (operateSymbol.peek() == "(") {
							break;
						} else {
							postfix.add(operateSymbol.pop());
						}
					}
					operateSymbol.push(infix.poll());
				}
			} else if (infix.peek() == "*" || infix.peek() == "/") {
				// ��ջΪ�ջ�ջ��Ԫ��Ϊ"(",��ֱ��ѹջ
				// ��ջ��Ϊ��,���ж�ջ��Ԫ�ص����ȼ�,�����ڻ����"*",���䵯��ֱ��ջ��Ԫ�����ȼ�С��"*"
				// ����"*"ѹջ
				if (operateSymbol.empty() || operateSymbol.peek() == "(") {
					operateSymbol.push(infix.poll());
				} else if (operateSymbol.peek() == "+" || operateSymbol.peek() == "-") {
					operateSymbol.push(infix.poll());
				} else {
					while (!operateSymbol.empty()) {
						if (operateSymbol.peek() == "*" || operateSymbol.peek() == "/") {
							postfix.add(operateSymbol.pop());
						} else if(operateSymbol.peek() == "("){
							break;
						}
					}
				}
				operateSymbol.push(infix.poll());
				
			} else {
				postfix.add(infix.poll());
			}
			
		}
		
		while (!operateSymbol.empty()) {
			postfix.add(operateSymbol.pop());
		}
		
		return postfix;

	}

	public static void main(String[] args) {
		Queue<String> infix = new LinkedList<>();
		String strings = "32+4*(5-2)-6/(1+2)";
		for (int i = 0; i < strings.length(); i++) {
			infix.add(String.valueOf(strings.charAt(i)));
		}
		//System.out.print(infix);
		Queue<String> postfix = new LinkedList<>();
		postfix = xx.getPostfix(infix);
		/*while (!postfix.isEmpty()) {
			System.out.print(postfix.poll());
		}*/
	}
}
