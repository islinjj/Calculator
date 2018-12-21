package com.niuer.ljj.������;
 
import java.util.HashMap;
import java.util.Stack;
 
public class test {
 
	//��������ȼ�
	private static HashMap<String,Integer> opLs;
	
	private String src;
 
	public test(String src) {
		this.src = src;
		if(opLs==null)
		{
			opLs = new HashMap<String,Integer>(6);
			opLs.put("+",0);
			opLs.put("-",0);
			opLs.put("*",1);
			opLs.put("/",1);
			opLs.put("%",1);
			opLs.put(")",2);
		}
	}
 
	//����׺���ʽת��Ϊ��׺���ʽ
	public String toRpn()
	{
		String[] tmp = split(src);
		/*for (int i = 0;i<src.length();i++) {
			System.out.print(tmp[i]);
		}*/
		// ��׺ջ
		Stack<String> rpn = new Stack<String>();
		// ��ʱջ
		Stack<String> tmpSta = new Stack<String>();
 
		for (String str : tmp) {
			if (isNum(str)) {
				//�ǲ�����,ֱ��ѹ����ջ
				rpn.push('('+str+')');
			}else{
				//�ǲ�������
				if(tmpSta.isEmpty())
				{//��û�з���
					tmpSta.push(str);
				}else{
				 //�ж���ǰ���ź���ʱջջ�����ŵ����ȼ�
					if(isHigh(tmpSta.peek(),str))
					{
						if(!str.equals(")"))
						{
							do{
								//1����ʱջ���ҳ�С�ڵ�ǰ���ȼ��Ĳ�����
								//2ѹ�뵱ǰ���������
								rpn.push(tmpSta.peek());
								tmpSta.pop();
							}while(!tmpSta.isEmpty()&&(isHigh(tmpSta.peek(),str)));
							
							tmpSta.push(str);
						}else{
							//��)���ε�����ʱջ�����ݣ�ֱ��(Ϊֹ
							while(!tmpSta.isEmpty()&&!tmpSta.peek().equals("("))
							{
								rpn.push(tmpSta.pop());
							}
							if((!tmpSta.empty())&&(tmpSta.peek().equals("(")))
							{//����(
								tmpSta.pop();
							}
						}
					}else if(!isHigh(tmpSta.peek(),str)){
						tmpSta.push(str);
					}
				}
			}
 
		}
		while(!tmpSta.empty())
		{//��ջ��ʣ��Ĳ��������ε���
			rpn.push(tmpSta.pop());
		}
		StringBuilder st = new StringBuilder();
		for (String str : rpn) {
        		st.append(str);
		}
		rpn.clear();
		return st.toString();
	}//�沨��
 
	//�ָ�(56+4)3*6+2=>(,56,+,4,
	private String[] split(String src) {
		StringBuilder sb = new StringBuilder(src.length());
		for(char ch:src.toCharArray())
		{
			if(ch=='+'||ch=='-'||ch=='*'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='%')
			{
				sb.append(",");
				sb.append(ch);
				sb.append(",");
			}else{
				sb.append(ch);
			}
		}
		
		String string = sb.toString().replaceAll(",{2,}", ",");
		//System.out.println(string);
		return string.split(",");
	}//�ָ�
 
	//�Ƚϲ����������ȼ�
	private boolean isHigh(String pop, String str) {
		if(str.equals(")"))
			return true;
		if(opLs.get(pop)==null||opLs.get(str)==null)
		  return false;
		return opLs.get(pop)>=opLs.get(str);
			
	}
 
	//�Ƿ�������
	public boolean isNum(String str) {
		for (char ch : str.toCharArray()) {
			if(ch=='+'||ch=='-'||ch=='*'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='%')
				return false;
		}
		return true;
	}
 
	//�õ����
	public double getRes() {
		String rpn = toRpn();
		System.out.println(rpn);
		Stack<Double> res = new Stack<Double>();
		StringBuilder sb = new StringBuilder();
		for(char ch:rpn.toCharArray())
		{ 
			if(ch=='(')
			{
				continue;
			}else if(ch>='0'&&ch<='9'||ch=='.'){
				sb.append(ch);
			}else if(ch==')')
			{
				res.push(Double.valueOf(sb.toString()));
				sb = new StringBuilder();
			}else{
				 if(!res.empty())
				 {
					 Double x = res.pop();
					 Double y = res.pop();
					 switch (ch) {
					case '+':
						 res.push(y+x); 
						break;
					case '-':
						res.push(y-x); 
						break;
					case '*':
						res.push(y*x); 
						break;	
					case '%':
					case '/':	
						if(x!=0)
						{
							double rsd = ch=='%'?y%x:y/x;
							res.push(rsd); 
						}else{
							 System.out.println("��ĸΪ��");
							 res.clear();
							 return -1;
						}
						break;
					}
			}
			}
		}
		Double result = res.pop();
		res.clear();
		return result;
	}//���
 
	public static void main(String[] args) {
		String src2 = "32+4*(5-2)-6/(1+2)";//(32)(4)(5)(*+(2))-(6)(1)(/-(2))+
		test analyer = new test(src2);
		System.out.println(src2+"="+analyer.getRes());
	}
}
