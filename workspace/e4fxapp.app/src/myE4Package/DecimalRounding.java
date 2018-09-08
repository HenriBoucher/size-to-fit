package myE4Package;

import java.text.DecimalFormat;

public class DecimalRounding {
	private static DecimalFormat df3 = new DecimalFormat(".###");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Double myCos = Math.cos(1.7);
		System.out.println("value = " + myCos + " rounded " + df3.format(myCos));
		Double myCosRounded = Double.parseDouble(df3.format(myCos));
		System.out.println("rounded myCos " + myCosRounded);
		System.out.println(-0.129 == myCosRounded);
	}

}
