package solutions;

import java.util.Arrays;

public class DeepToString
{
	public static String intArray(int[] i)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int n : i)
			sb.append(n + ", ");
		sb.replace(sb.length() - 2, sb.length(), "");
		sb.append("]");
		return sb.toString();
	}

	public static String doubleArray(double[] d)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (double n : d)
			sb.append(n + ", ");
		sb.replace(sb.length() - 2, sb.length(), "");
		sb.append("]");
		return sb.toString();
	}

	public static String shortArray(short[] s)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (short n : s)
			sb.append(n + ", ");
		sb.replace(sb.length() - 2, sb.length(), "");
		sb.append("]");
		return sb.toString();
	}

	public static String booleanArray(boolean[] b)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (boolean n : b)
			sb.append((n ? "T" : "F") + ", ");
		sb.replace(sb.length() - 2, sb.length(), "");
		sb.append("]");
		return sb.toString();
	}

	public static String floatArray(float[] f)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (float n : f)
			sb.append(n + ", ");
		sb.replace(sb.length() - 2, sb.length(), "");
		sb.append("]");
		return sb.toString();
	}

	public static String charArray(char[] c)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (char n : c)
			sb.append("'" + n + "'" + ", ");
		sb.replace(sb.length() - 2, sb.length(), "");
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args)
	{
		int[] a = { 1, 2, 3, 4 };
		double[] d = { 1.2, 2.2, 3.23 };
		short[] s = { 1, 3, 4, 5, };
		float[] f = { 1.2f, 2.3f, 3.4f };
		boolean[] b = { true, false, false, true };
		char[] c = { 'a', 'c', '3', 'a' };
		System.out.println(intArray(a));
		System.out.println(doubleArray(d));
		System.out.println(shortArray(s));
		System.out.println(floatArray(f));
		System.out.println(booleanArray(b));
		System.out.println(charArray(c));
		System.out.println("Arrays.toString()");
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(d));
		System.out.println(Arrays.toString(s));
		System.out.println(Arrays.toString(f));
		System.out.println(Arrays.toString(b));
		System.out.println(Arrays.toString(c));
	}
}