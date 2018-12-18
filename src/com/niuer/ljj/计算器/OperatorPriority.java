package com.niuer.ljj.������;

/**
 * ʹ��ö�ٶ����ַ����ȼ�
 * 
 * @author thinkpad_ljj
 *
 */
public enum OperatorPriority {

	// �����ַ������ȼ�
	ADD("+", 1), SUB("-", 1), MUL("*", 2), DIV("/", 2), LEFT("(", 3), RIGHT(")", 3);

	// operatorΪ�ַ���priorityΪ���ȼ�
	private String operator;
	private int priority;

	private OperatorPriority(String operator, int priority) {
		this.operator = operator;
		this.priority = priority;
	}

	/*
	 * �Ƚ���������ȼ����������Ϊ0��������Ϊ������С����Ϊ����
	 */
	public static int cmp(String o1, String o2) {
		int p1 = 0;
		int p2 = 0;

		// ���������������ö���е���������ȼ��ֱ𸳸�p1��p2,����ʼ�ж��ĸ����ȼ���
		for (OperatorPriority o : OperatorPriority.values()) {
			// �ж�o1�����ȼ�
			if (o.operator.equals(o1)) {
				p1 = o.priority;
			}
			// �ж�o2�����ȼ�
			if (o.operator.equals(o2)) {
				p2 = o.priority;
			}
		}

		return p1 - p2;
	}

	/*
	 * �ж��Ƿ��������
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
