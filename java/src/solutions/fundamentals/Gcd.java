package solutions.fundamentals;

import java.util.Scanner;

public class Gcd
{
	public static int gcd(int p, int q)
	{
		if (q == 0)
			return p;
		int r = p % q;
		return gcd(q, r);
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		int q = in.nextInt();
		System.out.println(gcd(p, q));
		in.close();
	}
}