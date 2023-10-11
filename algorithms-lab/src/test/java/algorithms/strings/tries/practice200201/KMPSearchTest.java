package algorithms.strings.tries.practice200201;

import algorithms.context.AppTestContext;
import algorithms.strings.alphabet.Alphabet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestContext.class)
public class KMPSearchTest {
    @Autowired
    private Alphabet abcAlphabet;

    @Test
    public void name() {
        KMPSearch kmpSearch = new KMPSearch(abcAlphabet, "ABABAC");
        System.out.println(kmpSearch.search("AAAAAABBBBBABABAC"));
        System.out.println(kmpSearch.search("ABABAAAAAAAAA"));
    }
}
