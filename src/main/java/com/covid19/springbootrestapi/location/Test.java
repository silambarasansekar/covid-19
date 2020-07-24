package com.covid19.springbootrestapi.location;

public class Test {

	public static void main(String[] args) {
		int x =40000 / 360;
		System.out.println("x:"+x);
		float y = Float.parseFloat("79.6946010");
		int value = (int) (Math.cos(Math.PI *( y/180.0))*x);
		System.out.println("value:"+value);
		long longtitude =  (long) Math.abs((Float.parseFloat("12.2091660") - Float.parseFloat("11.2091650"))*x);
		System.out.println("longtitude:"+longtitude);
	}

}
