package com.niuer.ljj.������;

/**
 * ���㹫ʽ
 * 
 * @author thinkpad_ljj
 *
 */
public class Calculate {

	// ���
	private String result;

	// ������
	private String firstNum;
	private String secondNum;

	// ����������
	public void setValue(String f, String s) {
		this.firstNum = f;
		this.secondNum = s;
	}

	/*
	 * �ӷ����ӵ�λ��ʼ���𣬷�10��1
	 */
	public String add() {

		result = "";

		// ѡ���������г��Ƚϳ���
		int maxLength = firstNum.length() > secondNum.length() ? firstNum.length() : secondNum.length();
		int index = maxLength - 1;
		// ��λ
		int carry = 0;

		// ������һ�����Ƚ�С������ǰ�油0
		while (firstNum.length() < secondNum.length()) {
			firstNum = "0" + firstNum;
		}
		while (firstNum.length() > secondNum.length()) {
			secondNum = "0" + secondNum;
		}

		// ��ʼ����
		while (index >= 0) {
			// �ֱ�ȡ��ÿһλ��
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
	 * �������������������������Ϊ0�����Ϊ���������Ϊ����
	 */
	public String sub() {
		// ���
		result = "";

		// ��Ƿ���
		String sign = "";

		// �������򷵻�0
		if (firstNum.equals(secondNum)) {
			return "0";
		}

		// ����
		// ��һ�����ȵڶ�����С��λ��С������ֵС�����򽻻�firstNum��secondNum,������Ϊ-��
		if (firstNum.length() < secondNum.length()
				|| (firstNum.length() == secondNum.length() && firstNum.compareTo(secondNum) < 0)) {
			sign = "-";
			// ����
			String tmp = firstNum;
			firstNum = secondNum;
			secondNum = tmp;
		}

		// ��С����secondNum������λ����λ��0
		while (secondNum.length() < firstNum.length()) {
			secondNum = "0" + secondNum;
		}

		// �����ӵ�λ��ʼ����λ���
		int index = firstNum.length() - 1;
		int borrow = 0; // ��¼��λ
		while (index >= 0) {

			// r������¼��ǰλ�������Ľ����charAt������ȡ��ǰ��������λ�õ���ֵ
			int r = firstNum.charAt(index) - secondNum.charAt(index) + borrow;

			// ������r��С��0�򽫽�λ��ֵλ-1������r����10
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
	 * �˷�
	 */
	public String mul() {

		// ���λ��
		int carry;

		result = "";

		// ��secondNum��λ������,���firstNum�ĳ��ȳ���s2����ô����
		if (firstNum.length() < secondNum.length()) {
			String tmp = firstNum;
			firstNum = secondNum;
			secondNum = tmp;
		}

		// ���ַ���ת�����ַ�����
		char[] c1 = firstNum.toCharArray();
		char[] c2 = secondNum.toCharArray();

		// �洢һ����˵Ľ��,��ŵĻ��ǵߵ��ģ�Ҫ�ǵ�reverse
		StringBuilder sb;

		// ����һ���ַ�������0���������0��
		if (firstNum.equals("0") || secondNum.equals("0")) {
			return "0";
		} else {
			// ����������Ϊ0��ʱ��
			for (int i = c2.length - 1; i >= 0; i--) {
				sb = new StringBuilder();
				// ���������ֻ�ǡ�1��ֱ���������
				if (c2[i] == '1' && c2.length == 1) {
					result = String.valueOf(c1);
				} else {
					// ����������ֻ�ǡ�1����0��
					// ��������
					carry = 0;
					for (int j = c1.length - 1; j >= 0; j--) {
						// temp������ʱ��Ž��
						int temp;
						// ʹ��again�����ۼƵ���˽�����λ��ӳ���10��ʱ��,��ֹ���ʹ��carry���ͻ
						int again = 0;
						// ���ַ�ת�������ֺ����
						temp = Integer.parseInt(String.valueOf(c1[j])) * Integer.parseInt(String.valueOf(c2[i]));
						// �������������н�λ��

						// ����λ������Ӵ��ڵ���10
						if (carry + temp % 10 >= 10) {
							again++;
						}
						sb.append((temp % 10 + carry) % 10);
						carry = temp / 10 + again;
						// ���н�λ��Ҫʹ�������˵����Ϊ����
						if (j == 0 && carry != 0) {
							sb.append(carry);
						}
					}
					// ����������ۼ�
					sb = sb.reverse();
					// ��0
					int k = i;
					while (k < c2.length - 1) {
						sb.append("0");
						k++;
					}

					// ����˷����
					firstNum = sb.toString();
					secondNum = result;

					result = add();
				}
			}
		}

		return result;
	}

	/*
	 * ����
	 */
	public String div() {

		// ��
		int quotient;

		// ����
		int div = Integer.parseInt(secondNum);

		char[] divisor = firstNum.toCharArray();

		//��������ǡ�0�������ִ���
		if (secondNum == "0") {
			return "����";
		}
		// ��¼����
		StringBuilder remainder = new StringBuilder();
		int r;

		// ��
		StringBuilder sb = new StringBuilder();
		// ��¼������
		StringBuilder b = new StringBuilder();
		
		//�����������ͬ����ô�ͷ��ء�1�����ڴ˴�����Ͳ��ؽ���ѭ��
		if(firstNum.equals(secondNum)) {
			return "1";
		}

		//����ӵ�һλ������
		b.append(divisor[0]);
		
		//j������¼��������ÿһλ
		int j=0;
		
		// i������¼���Ĵ���
		for (int i = 0; i < divisor.length; i++) {
			// ʹ��divisor[i]���ַ������Ƚϵ���ASCII��ֵ
			if (Integer.parseInt(b.toString()) < div && i == 0) {
				// ����������ĵ�һλС�ڳ����������һλ������
				b.append(divisor[++j]);
			}
			// ��
			quotient = (Integer.parseInt(b.toString()) / div);
			sb.append(quotient);
			
			//���������λ�������˾��˳�
			if(j==divisor.length-1) {
				break;
			}
			
			// ����
			r = (Integer.parseInt(b.toString()) % div);
			
			//������Ϊ0������ӵ�remainder
			if(r!=0) {
				remainder.append(r);
			}else if(r==0) {
				//����Ϊ0�������һλ
				remainder.append(divisor[++j]);
			}
			
			//�������С�ڳ����ͼ��������һλ��ֱ��������С�ڳ��������ٸ���������������һ�ֵĳ�������
			if (Integer.parseInt(remainder.toString()) < div) {
				remainder.append(divisor[++j]);
			}
			
			// ������ֵ��������
			b = remainder;
			//��������
			remainder=new StringBuilder();
		}

		// ��������С����
		
		//ȥ��ǰ��ġ�0��
		result = sb.toString().replaceAll("^(0+)", "");

		return result;
	}

	// ��ת���,ʹ��jdk�Դ���reverse ����
	public String reverse(String result) {
		StringBuilder news = new StringBuilder(result);

		result = news.reverse().toString();

		// ȥ������0
		result = cut(result);

		return result;
	}

	// ȥ�������0
	public String cut(String s) {

		while (s.indexOf("0") == 0) {
			s = s.substring(1);
		}

		return s;
	}

}
