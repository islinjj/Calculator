package com.niuer.ljj.计算器;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 工具类,中缀表达式转后缀表达式
 * 
 * @author xu
 *
 */
public class xx {

	/**
	 * 中缀表达式转后缀表达式
	 * 
	 * @param infix
	 */
	public static Queue<String> getPostfix(Queue<String> infix) {
		System.out.print(infix);

		/** 后缀表达式栈 */
		Queue<String> postfix = new LinkedList<>();
		//System.out.println(infix);

		/** 暂存操作符的栈 */
		Stack<String> operateSymbol = new Stack<>();

		while (!infix.isEmpty()) {
			// 遇到"("直接将"("压栈,"("优先级最高
			if (infix.peek() == "(") {
				operateSymbol.push(infix.poll());
			} else if (infix.peek() == ")") {
				// 遇到")"则将遇到的第一个"("之前的元素全部放到后缀表达式队列中,然后再弹出"("
				while (operateSymbol.peek() != "(" && !operateSymbol.empty()) {
					postfix.add(operateSymbol.pop());
				}
				operateSymbol.pop();
			} else if (infix.peek() == "+" || infix.peek() == "-") {
				// 若栈为空或栈顶元素为"(",则直接压栈
				// 若栈不为空,则判断栈顶元素的优先级,若高于或等于"+",则将其弹出直到栈顶元素优先级小于"+"
				// 然后将"+"压栈
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
				// 若栈为空或栈顶元素为"(",则直接压栈
				// 若栈不为空,则判断栈顶元素的优先级,若高于或等于"*",则将其弹出直到栈顶元素优先级小于"*"
				// 否则将"*"压栈
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
