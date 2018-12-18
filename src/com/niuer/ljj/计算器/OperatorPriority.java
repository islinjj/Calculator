package com.niuer.ljj.计算器;

/**
 * 使用枚举定义字符优先级
 * 
 * @author thinkpad_ljj
 *
 */
public enum OperatorPriority {

	// 设置字符的优先级
	ADD("+", 1), SUB("-", 1), MUL("*", 2), DIV("/", 2), LEFT("(", 3), RIGHT(")", 3);

	// operator为字符，priority为优先级
	private String operator;
	private int priority;

	private OperatorPriority(String operator, int priority) {
		this.operator = operator;
		this.priority = priority;
	}

	/*
	 * 比较运算符优先级，若相等则为0，大于则为正数，小于则为负数
	 */
	public static int cmp(String o1, String o2) {
		int p1 = 0;
		int p2 = 0;

		// 传进来的运算符和枚举中的相等则将优先级分别赋给p1和p2,并开始判断哪个优先级高
		for (OperatorPriority o : OperatorPriority.values()) {
			// 判断o1的优先级
			if (o.operator.equals(o1)) {
				p1 = o.priority;
			}
			// 判断o2的优先级
			if (o.operator.equals(o2)) {
				p2 = o.priority;
			}
		}

		return p1 - p2;
	}

	/*
	 * 判断是否是运算符
	 */
	public static boolean isOperator(String s) {

		for (OperatorPriority o : OperatorPriority.values()) {
			if (o.operator.equals(s)) {
				return true;
			}
		}
		return false;
	}

}
