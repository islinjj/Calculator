package com.niuer.ljj.¼ÆËãÆ÷;

public class App {
	
	public static void main(String[] args) {
		Expression express = new Expression();
		
		Controller control = new Controller(express);
		
		CalculateUI ui = new CalculateUI();
		
		ui.setCallBack(control);
	}
}
