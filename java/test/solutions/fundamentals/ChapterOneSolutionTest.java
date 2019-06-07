package solutions.fundamentals;

import static org.junit.Assert.*;
import org.junit.Test;

public class ChapterOneSolutionTest
{
    @Test
    public void testParentheses()
    {
        String pattern = "[[]](){}[({})]";
        assertEquals(true, ChapterOneSolution.parentheses(pattern));
    }
}