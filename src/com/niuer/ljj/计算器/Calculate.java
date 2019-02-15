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

		// 存进位数
		int carry;

		result = "";

		// 将secondNum定位被乘数,如果firstNum的长度长过s2，那么交换
		if (firstNum.length() < secondNum.length()) {
			String tmp = firstNum;
			firstNum = secondNum;
			secondNum = tmp;
		}

		// 将字符串转换成字符数组
		char[] c1 = firstNum.toCharArray();
		char[] c2 = secondNum.toCharArray();

		// 存储一次相乘的结果,存放的会是颠倒的，要记得reverse
		StringBuilder sb;

		// 其中一个字符串等于0，则输出“0”
		if (firstNum.equals("0") || secondNum.equals("0")) {
			return "0";
		} else {
			// 两个数都不为0的时候
			for (int i = c2.length - 1; i >= 0; i--) {
				sb = new StringBuilder();
				// 如果被乘数只是“1”直接输出乘数
				if (c2[i] == '1' && c2.length == 1) {
					result = String.valueOf(c1);
				} else {
					// 当被乘数不只是“1”或“0”
					// 遍历乘数
					carry = 0;
					for (int j = c1.length - 1; j >= 0; j--) {
						// temp用来临时存放结果
						int temp;
						// 使用again用来累计当相乘结果与进位相加超过10的时候,防止多次使用carry会冲突
						int again = 0;
						// 将字符转换成数字后相乘
						temp = Integer.parseInt(String.valueOf(c1[j])) * Integer.parseInt(String.valueOf(c2[i]));
						// 如果个数相乘是有进位的

						// 当进位与结果相加大于等于10
						if (carry + temp % 10 >= 10) {
							again++;
						}
						sb.append((temp % 10 + carry) % 10);
						carry = temp / 10 + again;
						// 当有进位，要使得最后相乘的最高为有数
						if (j == 0 && carry != 0) {
							sb.append(carry);
						}
					}
					// 将结果进行累加
					sb = sb.reverse();
					// 补0
					int k = i;
					while (k < c2.length - 1) {
						sb.append("0");
						k++;
					}

					// 处理乘法相加
					firstNum = sb.toString();
					secondNum = result;

					result = add();
				}
			}
		}

		return result;
	}

	/*
	 * 除法
	 */
	public String div() {

		// 商
		int quotient;

		// 除数
		int div = Integer.parseInt(secondNum);

		char[] divisor = firstNum.toCharArray();

		//如果除数是“0”则会出现错误
		if (secondNum == "0") {
			return "错误";
		}
		// 记录余数
		StringBuilder remainder = new StringBuilder();
		int r;

		// 商
		StringBuilder sb = new StringBuilder();
		// 记录被除数
		StringBuilder b = new StringBuilder();
		
		//如果两个数相同，那么就返回“1”，在此处处理就不必进入循环
		if(firstNum.equals(secondNum)) {
			return "1";
		}

		//先添加第一位被除数
		b.append(divisor[0]);
		
		//j用来记录被除数的每一位
		int j=0;
		
		// i用来记录除的次数
		for (int i = 0; i < divisor.length; i++) {
			// 使用divisor[i]是字符处理，比较的是ASCII码值
			if (Integer.parseInt(b.toString()) < div && i == 0) {
				// 如果被除数的第一位小于除数则添加下一位被除数
				b.append(divisor[++j]);
			}
			// 商
			quotient = (Integer.parseInt(b.toString()) / div);
			sb.append(quotient);
			
			//如果被除数位数除完了就退出
			if(j==divisor.length-1) {
				break;
			}
			
			// 余数
			r = (Integer.parseInt(b.toString()) % div);
			
			//余数不为0，就添加到remainder
			if(r!=0) {
				remainder.append(r);
			}else if(r==0) {
				//余数为0就添加下一位
				remainder.append(divisor[++j]);
			}
			
			//如果余数小于除数就继续添加下一位，直到余数不小于除数，后再赋给被除数进行下一轮的除法运算
			if (Integer.parseInt(remainder.toString()) < div) {
				remainder.append(divisor[++j]);
			}
			
			// 余数赋值给被除数
			b = remainder;
			//更新余数
			remainder=new StringBuilder();
		}

		// 处理商是小数的
		
		//去掉前面的“0”
		result = sb.toString().replaceAll("^(0+)", "");

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

}
