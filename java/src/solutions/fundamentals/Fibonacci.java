package solutions.fundamentals;

import java.util.Arrays;

public class Fibonacci
{
	public static long F(int N)
	{
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		return F(N - 1) + F(N - 2);
	}

	static long[] fib = new long[100];

	public static long F2(int N)
	{
		if (N == 0)
		{
			fib[N] = 0;
			return fib[N];
		}
		if (N == 1)
		{
			fib[N] = 1;
			return fib[N];
		}
		if (fib[N - 1] != 0)
		{
			fib[N] = fib[N - 1] + fib[N - 2];
			return fib[N];
		}
		return F2(N - 1) + F2(N - 2);
	}

	public static void main(String[] args)
	{
		// for (int N = 0; N < 100; ++N)
		System.out.println(20 + " " + F2(20));

		System.out.println(Arrays.toString(fib));
	}
}