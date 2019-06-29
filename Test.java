import java.util.*;

public class Test {
    public static void main(String[] args) {
        LinkedList<String> ls = new LinkedList<>();
        System.out.println(ls.size());
        LinkedList<String> ll = new LinkedList<String>(Arrays.asList("hello", "world", "wuxuecheng"));
        ll.size();
        ll.get(1);
        ll.add("socool");
        ll.addFirst("first");
        ll.addLast("last");
        System.out.println(ll);
        ArrayList<String> al = new ArrayList<>();
    }
}