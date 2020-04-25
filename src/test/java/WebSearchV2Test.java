import org.bitnick.duckduckgo.web.WebSearchV2;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class WebSearchV2Test {

    @Test
    public void inheritanceTest() throws Exception {
        try (WebSearchV2 webSearchTest = WebSearchV2.instanceOf()) {
            List<String> testObj = webSearchTest.search("Test");

            testObj.forEach(System.out::println);
            assertNotNull(testObj);
        }
    }
}