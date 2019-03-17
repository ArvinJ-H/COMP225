package test;

import java.util.*;

public class stack {
	
public static void main(String[] args) {
	int x;
	Stack<Integer> s =new Stack<Integer>();
	s.push(1);
	s.push(2);
	s.push(3);
	
	x = s.pop();
	
	s.push(5);
	//System.out.println(x);
	System.out.println(s);
}
public static int max(Stack<Integer> s) {
	int maxValue = s.pop();
	
	while (!s.isEmpty()) {
		int next = s.pop();
		maxValue = Math.max(maxValue, next);
	}
	return maxValue;
}
}
