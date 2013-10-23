package com.reminder.src;

public class Reminder {

	public static void main(String[] args) 
	{
		String input = "public class Test {";
		System.out.println(input.substring(input.indexOf("class")+5, input.indexOf("{")).trim());

	}

}
