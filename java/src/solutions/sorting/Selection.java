package solutions.sorting;

public class Selection extends Sortor
{
	public Selection()
	{
		super();
	}

	@Override
	public <T> void sort(Comparable<T>[] a)
	{
		// sort array 'a' in ascending order
		int N = a.length;
		for (int i = 0; i < N; ++i)
		{
			int min = i;
			for (int j = i + 1; j < N; ++j)
				if (less(a[j], a[min]))
					min = j;
			exch(a, i, min);
		}
	}

	public static void main(String[] args)
	{
		Selection selection = new Selection();
		Integer a[] = { 2, 3, 4, 5, 2, 1, 3, 5, 6 };
		String[] s = { "hello", "world", "wuxuecheng", "haha" };
		selection.sort(a);
		selection.show(a);
		selection.sort(s);
		selection.show(s);
	}
}