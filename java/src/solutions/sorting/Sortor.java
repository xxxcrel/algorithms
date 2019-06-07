package solutions.sorting;

import edu.princeton.cs.algs4.*;

public abstract class Sortor
{
	protected Sortor()
	{
	}

	public abstract <T> void sort(Comparable<T>[] a);

	@SuppressWarnings("unchecked")
	protected <T> boolean less(Comparable<T> v, Comparable<T> w)
	{
		return v.compareTo((T) w) < 0;
	}

	protected <T> void exch(Comparable<T>[] a, int i, int j)
	{
		Comparable<T> t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	protected <T> void show(Comparable<T>[] a)
	{
		for (int i = 0; i < a.length; ++i)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}

	protected <T> boolean isSorted(Comparable<T>[] a)
	{
		for (int i = 1; i < a.length; ++i)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

}