package com.niuer.ljj.计算器;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 逆波兰表达式/后缀表示法（使用栈）
 * 
 * @author thinkpad_ljj
 *
 */
public class Poland {

	// digital用来判断是否是运算符，是运算符则返回真
	static boolean digital;

	/**
	 * 分割表达式,返回字符串数组
	 */
	private  static String[] split(String express) {
		//测试用例
		//express ="3+4*(5-2)-6/(1+2)";
		
		//sb用来存储分割后的字符串，容量为字符串大小
		
		StringBuilder sb = new StringBuilder(express.length());
		for (char ch : express.toCharArray()) {
			
			//使用正则表达式
			String str = "\\+|-|\\*|/|\\(|\\)";
			//如果和str匹配则就加入StringBuilder
			if(String.valueOf(ch).matches(str)) {
				//使用","分割
				sb.append(",");
				sb.append(ch);
				sb.append(",");
			} else {
				sb.append(ch);
			}
			
			//使用if进行分割
			/*if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')') {
				sb.append(",");
				sb.append(ch);
				sb.append(",");
			} else {
				sb.append(ch);
			}*/
		}

		//因为前面会导致逗号至少出现两次{2，}表示至少出现2次
		//replaceAll（正则表达式，替代物）.
		String string = sb.toString().replaceAll(",{2,}", ",");
		
		System.out.println(string);
		
		//返回以”，“为分隔符的字符串数组
		return string.split(",");
	}

	/**
	 * 逆波兰表达式
	 * 
	 * @param express
	 */
	public String ReversePoland(String express) {
		//将解析后的表达式存入tmp中
		String[] tmp = split(express);
	
		// 操作数进队列，运算符进栈，运算结果放入栈中
		Queue<String> q = new LinkedList<String>();
		Stack<String> s = new Stack<String>();

		// 判断左括号数量
		int left = 0;

		// 当还有字符串数组时分别将操作数和运算符入队列和栈
		for (String str : tmp) {
			
			// 判断是不是运算符，是运算符则返回真
			digital = OperatorPriority.isOperator(str);
			
			/**
			 *  如果不是运算符则入队列
			 */
			//使用（）区分出字符串
			if (!digital) {
				q.add("("+str+")");
			} else {
				/**
				 * 如果栈是空的直接入栈
				 */
				if (s.empty()) {
					s.push(str);
				}

				/**
				 * 如果是左括号则直接入栈
				 */
				else if (str.equals("(")) {
					s.push(str);
					left++;

				}

				/**
				 * left>0说明存在左括号
				 */
				else if (left > 0) {
					// 如果是“)”,就把“(”之前的都加入到队列当中
					if (str.equals(")")) {
						left--;
						while (!s.empty()) {
							if (s.peek().equals("(")) {
								// 把左括号去掉
								s.pop();
								break;
							} else {
								// 左括号前的入队列
								q.add(s.pop());
							}
						}
					}

					// 如果栈顶是“(”则直接入栈
					else if (s.peek().equals("("))
						s.push(str);

					// 如果还没遇到“(”则需要判断优先级入栈
					// 判断优先级后入栈，优先级比栈中大则入栈，与栈中相等或者比栈中小则先将栈中的运算符出栈后入栈
					// 大于0则说明优先级比栈中大，则直接入栈
					else if ((OperatorPriority.cmp(str, s.peek())) > 0 && !s.peek().equals("(")) {
						s.push(str);
					}
					// 小于等于0则说明优先级比栈中的小或者与栈中相等，把栈中的运算符弹出加入队列，直到栈中的运算符比它小
					// 且栈顶不是“（”才判断优先级
					else if ((OperatorPriority.cmp(str, s.peek())) <= 0 && !s.peek().equals( "(")) {
						// 把栈中的运算符弹出加入队列，直到栈中的运算符比它小
						while (!s.empty() && (OperatorPriority.cmp(str, s.peek())) <= 0 && !s.peek().equals("(")) {
							q.add(s.pop());
						}
						s.push(str);
					}

				} // 含有括号

				/**
				 * 判断优先级
				 */
				// 判断优先级后入栈，优先级比栈中大则入栈，与栈中相等或者比栈中小则先将栈中的运算符出栈后入栈
				// 大于0则说明优先级比栈中大，则直接入栈
				else if (!s.empty() && (OperatorPriority.cmp(str, s.peek())) > 0) {
					s.push(str);
				}
				/**
				 * 判断优先级
				 */
				// 小于等于0则说明优先级比栈中的小或者与栈中相等，把栈中的运算符弹出加入队列，直到栈中的运算符比它小
				else if (!s.empty() && (OperatorPriority.cmp(str, s.peek())) <= 0) {
					// 把栈中的运算符弹出加入队列，直到栈中的运算符比它小
					while (!s.empty() && (OperatorPriority.cmp(str, s.peek())) <= 0) {
						q.add(s.pop());
					}
					s.push(str);
				}

			} // 运算符
			
		} // 逆波兰操作

		/**
		 *  遍历结束，将运算符全部入队列
		 */
		while (!s.empty()) {
			q.add(s.pop());
		}
		//将队列中的元素放入StringBuilder,拼接在一起
		StringBuilder st = new StringBuilder();
		for (String str : q) {
        		st.append(str);
		}
		//System.out.print(news);
		return st.toString();
	}

	public static void main(String[] args) {
		// 测试表达式
		String express = "2+7*(1+10)"; //(32)(4)(5)(2)-*+(6)(1)(2)+/-
		split(express);
		// 逆波兰表达式
		//express = ReversePoland(express);
		//System.out.print(express);
	}

}
