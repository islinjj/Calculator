package com.niuer.ljj.计算器;

/**
 * 计算公式
 * 
 * @author thinkpad_ljj
 *
 */
public class Calculate {

	// 结果
	private String result;

	// 运算数
	private String firstNum;
	private String secondNum;

	// 设置两个数
	public void setValue(String f, String s) {
		this.firstNum = f;
		this.secondNum = s;
	}

	/*
	 * 加法，从低位开始加起，逢10进1
	 */
	public String add() {

		result = "";

		// 选择两个数中长度较长的
		int maxLength = firstNum.length() > secondNum.length() ? firstNum.length() : secondNum.length();
		int index = maxLength - 1;
		// 进位
		int carry = 0;

		// 当其中一个长度较小的则在前面补0
		while (firstNum.length() < secondNum.length()) {
			firstNum = "0" + firstNum;
		}
		while (firstNum.length() > secondNum.length()) {
			secondNum = "0" + secondNum;
		}

		// 开始计算
		while (index >= 0) {
			// 分别取出每一位数
			int firstInt = Integer.valueOf(firstNum.substring(index, index + 1));
			int secondInt = Integer.valueOf(secondNum.substring(index, index + 1));

			result += (firstInt + secondInt + carry) % 10;
			carry = (firstInt + secondInt + carry) / 10;

			index--;
		}

		if (carry != 0) {
			result += carry;
		}
		return reverse(result);

	}

	/*
	 * 减法，三种情况：两个相等相减为0，相减为正数，相减为负数
	 */
	public String sub() {
		// 结果
		result = "";

		// 标记符号
		String sign = "";

		// 如果相等则返回0
		if (firstNum.equals(secondNum)) {
			return "0";
		}

		// 负数
		// 第一个数比第二个数小（位数小或者数值小），则交换firstNum与secondNum,符号置为-，
		if (firstNum.length() < secondNum.length()
				|| (firstNum.length() == secondNum.length() && firstNum.compareTo(secondNum) < 0)) {
			sign = "-";
			// 交换
			String tmp = firstNum;
			firstNum = secondNum;
			secondNum = tmp;
		}

		// 若小数（secondNum）不足位，高位补0
		while (secondNum.length() < firstNum.length()) {
			secondNum = "0" + secondNum;
		}

		// 大数从低位开始，逐位相减
		int index = firstNum.length() - 1;
		int borrow = 0; // 记录借位
		while (index >= 0) {

			// r用来记录当前位数相减后的结果，charAt用来获取当前索引所在位置的数值
			int r = firstNum.charAt(index) - secondNum.charAt(index) + borrow;

			// 相减后的r若小于0则将借位赋值位-1，并将r加上10
			if (r < 0) {
				borrow = -1;
				r += 10;
			}

			//
			result += r;
			index--;
		}

		return sign + reverse(result);
	}

	/*
	 * 乘法
	 */
	public String mul() {

		int r = (Integer.parseInt(firstNum)) * (Integer.parseInt(secondNum));

		result = String.valueOf(r);

		return result;
	}

	/*
	 * 除法
	 */
	public String div() {
		int r = (Integer.parseInt(firstNum)) / (Integer.parseInt(secondNum));

		result = String.valueOf(r);
		return result;
	}

	// 翻转结果,使用jdk自带的reverse 方法
	public String reverse(String result) {
		StringBuilder news = new StringBuilder(result);

		result = news.reverse().toString();

		// 去除多余0
		result = cut(result);

		return result;
	}

	// 去除多余的0
	public String cut(String s) {

		while (s.indexOf("0") == 0) {
			s = s.substring(1);
		}

		return s;
	}

	// 测试
	/*
	 * public static void main(String[] args) { Calculate c = new Calculate();
	 * c.setValue("001100", "0"); System.out.println(c.add()); }
	 */
}
