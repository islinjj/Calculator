package com.niuer.ljj.计算器;

import java.nio.charset.MalformedInputException;
import java.util.Stack;

public class Expression {

	Calculate calculate = new Calculate();

	static // 接收逆波兰表达式并对其进行计算
	Poland poland = new Poland();

	// 定义栈存储结果
	Stack<String> output = new Stack<String>();

	// 表达式
	String expression;

	// 第一个操作数和第二个操作数
	String first;
	String second;

	// 结果
	String result;

	/**
	 * 设置表达式
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * 将逆波兰表达式赋值给expression
	 */

	/**
	 * 开始计算，并返回结果
	 * 
	 * @return
	 */
	public String CalExpression() {
		//测试传过来的逆波兰表达式是否被分割,,,,,,将逆波兰表达式赋值给expression
		//String expression = "32+4*(5-2)-6/(1+2)";
		
		//逆波兰表达式，存储的时被分割后的字符串带有()进行区分
		expression = poland.ReversePoland(expression);
		
		//System.out.println(expression);
		
		// 判断是不是运算符
		boolean digital;
		
		//使用StringBuilder来拼接
		StringBuilder sb = new StringBuilder();
		/**
		 * 开始计算，作为字符更好处理
		 */
		for (char c : expression.toCharArray()) {
			
			//判断是不是运算符,是运算符为真
			digital = OperatorPriority.isOperator(String.valueOf(c));
			
			if(c == '(')
				continue;
			else if(!digital) {
				sb.append(c);
			}
			//是“）”则说明遇到完整数字
			else if(c == ')') {
				output.push(String.valueOf(sb));
				//清空sb
				sb = new StringBuilder();
			}else {
				//遇到的是运算符
				if(!output.isEmpty()) {
					first = output.pop();
					second = output.pop();
					//因为使用
					calculate.setValue(second, first);
					switch (c) {
					case '+':
						output.push(result = calculate.add());
						break;
					case '-':
						output.push(result = calculate.sub());
						break;
					case '*':
						output.push(result = calculate.mul());
						break;
					case '/':
						output.push(result = calculate.div());
						break;
					}
				}
				
			}//运算符处理
		}//开始计算
		
		/*first = new String();
		second = new String();*/

		// 先进行解析
		//analysis();

		// 将两个数值传到Calculate
		//calculate.setValue(first, second);
		/*// 判断运算符，并进行相应的算术
		if (operator == '+') {
			result = calculate.add();
		} else if (operator == '-') {
			result = calculate.sub();
		} else if (operator == '*') {
			result = calculate.mul();
		} else if (operator == '/') {
			result = calculate.div();
		}*/

		/*if(output.isEmpty()) {
			result = String.valueOf(output.pop());
		}*/
		return output.pop();

	}

	/**
	 * 解析表达式
	 */
	/*private void analysis() {

		// 使用正则表达式
		
		  String[] news = expression.split("\\+|\\*|-|/"); for (String string : news) {
		  first +=String.valueOf(string); }
		 

		int i;

		// 解析出第一个数值
		for (i = 0; i < expression.length(); i++) {

			// c为表达式中的数值或者运算符
			char c = expression.charAt(i);

			// 将数值作为字符串存入first
			if (c != '+' && c != '-' && c != '*' && c != '/') {
				first += String.valueOf(c);
			} else {
				// 运算符
				operator = c;
				break;
			}
		}

		// 解析出第二个数值
		for (i = i + 1; i < expression.length(); i++) {

			char c = expression.charAt(i);
			second += String.valueOf(c);
		}
	}*/
	
	/*public static void main(String[] args) {
		Expression express = new Expression();
		String newexpress = express.CalExpression();
		//System.out.println(newexpress);
		
	}*/

}
