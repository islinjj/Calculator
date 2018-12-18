package com.niuer.ljj.计算器;

import com.niuer.ljj.计算器.CalculateUI.CallBack;

public class Controller implements CallBack {

	Expression expression;
	
	//Poland poland;
	
	public Controller(Expression expression) {
		this.expression = expression;
	}
	
	//传表达式,此处传逆波兰表达式
	@Override
	public void setExpression(String express) {
		expression.setExpression(express);
	}

	//获得运算结果
	@Override
	public String getResult() {
		return expression.CalExpression();
	}
	
}
