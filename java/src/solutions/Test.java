package list;

import java.util.*;
import static net.mindview.util.Print.*;

public class Test
{
    public static void main(String[] args)
    {
        java.util.ArrayList<String> sList = new java.util.ArrayList<>();
        sList.add("wuxuecheng");
        sList.add("so");
        sList.add("cool");
        sList.add("very");
        for (String s : sList)
            print(s);
        Iterator<String> iter = sList.iterator();
        ListIterator<?> listIter = sList.listIterator();
        for (int i = 0; i < 4; ++i)
        {
            // print("iter.next()" + iter.next());
            print("listItr.nextIndex()" + listIter.nextIndex());
            print("listIter.next()" + listIter.next());
            print("listIter.previousIndex()" + listIter.previousIndex());
        }
        // while (iter.hasNext())
        // print(iter.next());
        // Iterator<?> iter2 = sList.iterator();
        // print(iter2.getClass().getSimpleName());
        // while (iter2.hasNext())
        // print(iter2.next());
        // Iterator<String> iter3 = sList.listIterator();
        // print(iter3.getClass().getSimpleName());
    }
}