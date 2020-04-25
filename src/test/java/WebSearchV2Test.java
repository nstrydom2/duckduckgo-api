import org.bitnick.duckduckgo.web.WebSearchV2;
import org.bitnick.duckduckgo.web.search.Result;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class WebSearchV2Test {

    @Test
    public void inheritanceTest() throws Exception {
        try (WebSearchV2 webSearchTest = WebSearchV2.instanceOf()) {
            List<Result> testObj = webSearchTest.search("Test");

            testObj.forEach(result -> {
                    System.out.println(result.getTitle());
                    System.out.println(result.getUrl());
                    System.out.println(result.getDescription());
                    System.out.println();
            });
            assertNotNull(testObj);
        }
    }
}
