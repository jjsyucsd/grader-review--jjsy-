import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

public class TestListExamples {
    @Test
    public void testMergeRightEnd() {
        List<String> left = Arrays.asList("a", "b", "c");
        List<String> right = Arrays.asList("a", "d");
        List<String> merged = ListExamples.merge(left, right);
        List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
        assertEquals(expected, merged);
    }

    @Test
    public void testFilter_allMatch() {
        List<String> input = Arrays.asList("apple", "apricot", "banana");
        StringChecker sc = new StringCheckerImpl("a");
        List<String> expected = Arrays.asList("apple", "apricot", "banana"); // Include "banana" as it contains "a"
        assertEquals(expected, ListExamples.filter(input, sc));
}


    @Test
    public void testFilter_noneMatch() {
        List<String> input = Arrays.asList("apple", "apricot", "banana");
        StringChecker sc = new StringCheckerImpl("z");
        List<String> expected = Arrays.asList();
        assertEquals(expected, ListExamples.filter(input, sc));
    }

    @Test
    public void testFilter_emptyList() {
        List<String> input = Arrays.asList();
        StringChecker sc = new StringCheckerImpl("a");
        List<String> expected = Arrays.asList();
        assertEquals(expected, ListExamples.filter(input, sc));
    }

    @Test
    public void testMerge_bothListsNonEmpty() {
        List<String> list1 = Arrays.asList("a", "c", "e");
        List<String> list2 = Arrays.asList("b", "d", "f");
        List<String> expected = Arrays.asList("a", "b", "c", "d", "e", "f");
        assertEquals(expected, ListExamples.merge(list1, list2));
    }

    @Test
    public void testMerge_firstListEmpty() {
        List<String> list1 = Arrays.asList();
        List<String> list2 = Arrays.asList("b", "d", "f");
        List<String> expected = Arrays.asList("b", "d", "f");
        assertEquals(expected, ListExamples.merge(list1, list2));
    }

    @Test
    public void testMerge_secondListEmpty() {
        List<String> list1 = Arrays.asList("a", "c", "e");
        List<String> list2 = Arrays.asList();
        List<String> expected = Arrays.asList("a", "c", "e");
        assertEquals(expected, ListExamples.merge(list1, list2));
    }

    @Test
    public void testMerge_bothListsEmpty() {
        List<String> list1 = Arrays.asList();
        List<String> list2 = Arrays.asList();
        List<String> expected = Arrays.asList();
        assertEquals(expected, ListExamples.merge(list1, list2));
    }
}

class StringCheckerImpl implements StringChecker {
    private String validPattern;

    public StringCheckerImpl(String pattern) {
        this.validPattern = pattern;
    }

    @Override
    public boolean checkString(String s) {
        return s.contains(validPattern);
    }
}
