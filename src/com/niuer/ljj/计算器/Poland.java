package com.niuer.ljj.������;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * �沨�����ʽ/��׺��ʾ����ʹ��ջ��
 * 
 * @author thinkpad_ljj
 *
 */
public class Poland {

	// digital�����ж��Ƿ������������������򷵻���
	static boolean digital;

	/**
	 * �ָ���ʽ,�����ַ�������
	 */
	private  static String[] split(String express) {
		//��������
		//express ="3+4*(5-2)-6/(1+2)";
		
		//sb�����洢�ָ����ַ���������Ϊ�ַ�����С
		
		StringBuilder sb = new StringBuilder(express.length());
		for (char ch : express.toCharArray()) {
			
			//ʹ��������ʽ
			String str = "\\+|-|\\*|/|\\(|\\)";
			//�����strƥ����ͼ���StringBuilder
			if(String.valueOf(ch).matches(str)) {
				//ʹ��","�ָ�
				sb.append(",");
				sb.append(ch);
				sb.append(",");
			} else {
				sb.append(ch);
			}
			
			//ʹ��if���зָ�
			/*if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')') {
				sb.append(",");
				sb.append(ch);
				sb.append(",");
			} else {
				sb.append(ch);
			}*/
		}

		//��Ϊǰ��ᵼ�¶������ٳ�������{2��}��ʾ���ٳ���2��
		//replaceAll��������ʽ������.
		String string = sb.toString().replaceAll(",{2,}", ",");
		
		System.out.println(string);
		
		//�����ԡ�����Ϊ�ָ������ַ�������
		return string.split(",");
	}

	/**
	 * �沨�����ʽ
	 * 
	 * @param express
	 */
	public String ReversePoland(String express) {
		//��������ı��ʽ����tmp��
		String[] tmp = split(express);
	
		// �����������У��������ջ������������ջ��
		Queue<String> q = new LinkedList<String>();
		Stack<String> s = new Stack<String>();

		// �ж�����������
		int left = 0;

		// �������ַ�������ʱ�ֱ𽫲����������������к�ջ
		for (String str : tmp) {
			
			// �ж��ǲ������������������򷵻���
			digital = OperatorPriority.isOperator(str);
			
			/**
			 *  �������������������
			 */
			//ʹ�ã������ֳ��ַ���
			if (!digital) {
				q.add("("+str+")");
			} else {
				/**
				 * ���ջ�ǿյ�ֱ����ջ
				 */
				if (s.empty()) {
					s.push(str);
				}

				/**
				 * �������������ֱ����ջ
				 */
				else if (str.equals("(")) {
					s.push(str);
					left++;

				}

				/**
				 * left>0˵������������
				 */
				else if (left > 0) {
					// ����ǡ�)��,�Ͱѡ�(��֮ǰ�Ķ����뵽���е���
					if (str.equals(")")) {
						left--;
						while (!s.empty()) {
							if (s.peek().equals("(")) {
								// ��������ȥ��
								s.pop();
								break;
							} else {
								// ������ǰ�������
								q.add(s.pop());
							}
						}
					}

					// ���ջ���ǡ�(����ֱ����ջ
					else if (s.peek().equals("("))
						s.push(str);

					// �����û������(������Ҫ�ж����ȼ���ջ
					// �ж����ȼ�����ջ�����ȼ���ջ�д�����ջ����ջ����Ȼ��߱�ջ��С���Ƚ�ջ�е��������ջ����ջ
					// ����0��˵�����ȼ���ջ�д���ֱ����ջ
					else if ((OperatorPriority.cmp(str, s.peek())) > 0 && !s.peek().equals("(")) {
						s.push(str);
					}
					// С�ڵ���0��˵�����ȼ���ջ�е�С������ջ����ȣ���ջ�е����������������У�ֱ��ջ�е����������С
					// ��ջ�����ǡ��������ж����ȼ�
					else if ((OperatorPriority.cmp(str, s.peek())) <= 0 && !s.peek().equals( "(")) {
						// ��ջ�е����������������У�ֱ��ջ�е����������С
						while (!s.empty() && (OperatorPriority.cmp(str, s.peek())) <= 0 && !s.peek().equals("(")) {
							q.add(s.pop());
						}
						s.push(str);
					}

				} // ��������

				/**
				 * �ж����ȼ�
				 */
				// �ж����ȼ�����ջ�����ȼ���ջ�д�����ջ����ջ����Ȼ��߱�ջ��С���Ƚ�ջ�е��������ջ����ջ
				// ����0��˵�����ȼ���ջ�д���ֱ����ջ
				else if (!s.empty() && (OperatorPriority.cmp(str, s.peek())) > 0) {
					s.push(str);
				}
				/**
				 * �ж����ȼ�
				 */
				// С�ڵ���0��˵�����ȼ���ջ�е�С������ջ����ȣ���ջ�е����������������У�ֱ��ջ�е����������С
				else if (!s.empty() && (OperatorPriority.cmp(str, s.peek())) <= 0) {
					// ��ջ�е����������������У�ֱ��ջ�е����������С
					while (!s.empty() && (OperatorPriority.cmp(str, s.peek())) <= 0) {
						q.add(s.pop());
					}
					s.push(str);
				}

			} // �����
			
		} // �沨������

		/**
		 *  �����������������ȫ�������
		 */
		while (!s.empty()) {
			q.add(s.pop());
		}
		//�������е�Ԫ�ط���StringBuilder,ƴ����һ��
		StringBuilder st = new StringBuilder();
		for (String str : q) {
        		st.append(str);
		}
		//System.out.print(news);
		return st.toString();
	}

	public static void main(String[] args) {
		// ���Ա��ʽ
		String express = "2+7*(1+10)"; //(32)(4)(5)(2)-*+(6)(1)(2)+/-
		split(express);
		// �沨�����ʽ
		//express = ReversePoland(express);
		//System.out.print(express);
	}

}
