package com.niuer.ljj.������;

import com.niuer.ljj.������.CalculateUI.CallBack;

public class Controller implements CallBack {

	Expression expression;
	
	//Poland poland;
	
	public Controller(Expression expression) {
		this.expression = expression;
	}
	
	//�����ʽ,�˴����沨�����ʽ
	@Override
	public void setExpression(String express) {
		expression.setExpression(express);
	}

	//���������
	@Override
	public String getResult() {
		return expression.CalExpression();
	}
	
}
