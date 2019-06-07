package solutions.fundamentals;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import solutions.DeepToString;
import edu.princeton.cs.algs4.Stack;

public class ChapterOneSolution
{
    /**
     * exercise 1.1.13
     *
     * @author wuxuecheng
     *
     * @date 2019.6.3
     */

    public static void arrayInversion(int[][] a)
    {
        if (a == null)
            return;
        int oriRow = a.length;
        int oriCol = a[0].length;
        for (int invRow = 0; invRow < oriCol; ++invRow)
        {
            System.out.print("[ ");
            for (int invCol = 0; invCol < oriRow; ++invCol)
                System.out.print(a[invCol][invRow] + " ");

            System.out.println("]");
        }
    }

    /**
     * exercise 1.1.14 if N is 0 return -1 assume N is poitive Integer
     *
     * @author wuxuecheng
     *
     * @date 2019.6.3
     */
    public static int lg(int N)
    {
        if (N == 0)
            return -1;
        if (N == 1)
            return 0;
        int n = 0;
        int r = 0;
        for (; n < Integer.MAX_VALUE; ++n)
        {
            r = pow(2, n);
            if (r >= N)
            {
                if (r == N)
                    return n;
                else
                    return n - 1;
            }
        }
        return 0;
    }

    /**
     * a simple pow() method use recursive assume n is positive Interger
     */
    public static int pow(int base, int n)
    {
        if (n == 0)
            return 1;
        if (n == 1)
            return base;
        if (n % 2 == 0)
            return pow(base * base, n / 2);
        else if (n % 2 == 1)
            return pow(base * base, n / 2) * base;
        return 0;
    }

    /**
     * exercise 1.1.15
     *
     * @author wuxuecheng
     */
    public static int[] countArray(int[] a, int m)
    {
        int[] am = new int[m];
        for (int i : a)
            if (i < m)
                am[i]++;
        return am;
    }

    /**
     * exercise 1.1.30 if 'i' and 'j' is co-prime then it present 'true' else
     * 'false'
     *
     * @param N create a N * N boolean matrix
     *
     * @author wuxuecheng
     */

    public static boolean[][] CoPrime(int N)
    {
        boolean[][] a = new boolean[N][N];
        for (int row = 0; row < a.length; ++row)
            for (int col = row; col < a[row].length; ++col)
                if (isCoPirme(row, col))
                {
                    a[row][col] = true;
                    a[col][row] = true;
                }
        return a;
    }

    public static boolean isCoPirme(int i, int j)
    {
        return Gcd.gcd(i, j) == 1 ? true : false;
    }

    /**
     * exercise 1.3.4 use the Stack of algs4/fundamentals
     *
     * @author wuxuecheng
     * @param args
     */

    public static boolean parentheses(String s) throws IllegalArgumentException
    {
        char[] pattern = s.toCharArray();
        Stack<Character> parStack = new Stack<Character>();
        int i = 0;
        while (i < pattern.length)
        {
            switch (pattern[i])
            {
                case '[':
                case '(':
                case '{':
                    parStack.push(pattern[i]);
                    break;
                case ']':
                    if (parStack.peek() == '[')
                        parStack.pop();
                    else
                        parStack.push(pattern[i]);
                    break;
                case ')':
                    if (parStack.peek() == '(')
                        parStack.pop();
                    else
                        parStack.push(pattern[i]);
                    break;
                case '}':
                    if (parStack.peek() == '{')
                        parStack.pop();
                    else
                        parStack.push(pattern[i]);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            ++i;
        }
        return parStack.isEmpty();
    }

    public static void main(String[] args)
    {
        int[][] a = { { 2, 3, 4 }, { 4, 5, 6 }, { 2, 3, 5 }, { 4, 5, 6 } };
        arrayInversion(a);
        System.out.println(pow(2, 7));
        System.out.println(lg(123));
        int[] aa = { 1, 2, 3, 2, 3, 4, 2, 3, 3, 2, 2, 2, 2, 3, 3, 0, 0, 0 };
        System.out.println(Arrays.toString(countArray(aa, 3)));
        boolean[][] b = CoPrime(10);
        for (boolean[] fi : b)
            System.out.println(DeepToString.booleanArray(fi));
        In in = new In();
        String parentheses = in.readString();
        System.out.println(parentheses(parentheses));
    }
}