package com.niuer.ljj.������;

import java.nio.charset.MalformedInputException;
import java.util.Stack;

public class Expression {

	Calculate calculate = new Calculate();

	static // �����沨�����ʽ��������м���
	Poland poland = new Poland();

	// ����ջ�洢���
	Stack<String> output = new Stack<String>();

	// ���ʽ
	String expression;

	// ��һ���������͵ڶ���������
	String first;
	String second;

	// ���
	String result;

	/**
	 * ���ñ��ʽ
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * ���沨�����ʽ��ֵ��expression
	 */

	/**
	 * ��ʼ���㣬�����ؽ��
	 * 
	 * @return
	 */
	public String CalExpression() {
		//���Դ��������沨�����ʽ�Ƿ񱻷ָ�,,,,,,���沨�����ʽ��ֵ��expression
		//String expression = "32+4*(5-2)-6/(1+2)";
		
		//�沨�����ʽ���洢��ʱ���ָ����ַ�������()��������
		expression = poland.ReversePoland(expression);
		
		//System.out.println(expression);
		
		// �ж��ǲ��������
		boolean digital;
		
		//ʹ��StringBuilder��ƴ��
		StringBuilder sb = new StringBuilder();
		/**
		 * ��ʼ���㣬��Ϊ�ַ����ô���
		 */
		for (char c : expression.toCharArray()) {
			
			//�ж��ǲ��������,�������Ϊ��
			digital = OperatorPriority.isOperator(String.valueOf(c));
			
			if(c == '(')
				continue;
			else if(!digital) {
				sb.append(c);
			}
			//�ǡ�������˵��������������
			else if(c == ')') {
				output.push(String.valueOf(sb));
				//���sb
				sb = new StringBuilder();
			}else {
				//�������������
				if(!output.isEmpty()) {
					first = output.pop();
					second = output.pop();
					//��Ϊʹ��
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
				
			}//���������
		}//��ʼ����
		
		/*first = new String();
		second = new String();*/

		// �Ƚ��н���
		//analysis();

		// ��������ֵ����Calculate
		//calculate.setValue(first, second);
		/*// �ж����������������Ӧ������
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
	 * �������ʽ
	 */
	/*private void analysis() {

		// ʹ��������ʽ
		
		  String[] news = expression.split("\\+|\\*|-|/"); for (String string : news) {
		  first +=String.valueOf(string); }
		 

		int i;

		// ��������һ����ֵ
		for (i = 0; i < expression.length(); i++) {

			// cΪ���ʽ�е���ֵ���������
			char c = expression.charAt(i);

			// ����ֵ��Ϊ�ַ�������first
			if (c != '+' && c != '-' && c != '*' && c != '/') {
				first += String.valueOf(c);
			} else {
				// �����
				operator = c;
				break;
			}
		}

		// �������ڶ�����ֵ
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
