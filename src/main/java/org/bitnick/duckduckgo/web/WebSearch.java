package org.bitnick.duckduckgo.web; /**
 * Class for creating an object to make queries to
 * duckduckgo in a Java API
 *
 * @author Nicholas Strydom
 * @version 0.1.5
 */

import java.util.*;

import org.bitnick.duckduckgo.web.search.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebSearch implements AutoCloseable {

	/**
	 * GET method to retrieve web page with results.
	 * @param query Search query for duckduckgo
	 * @return
	 * @throws Exception
	 */
	public Document getPage(String query) throws Exception {
		final String DUCKDUCKGO_URL = "https://duckduckgo.com/html?q=";

		return Jsoup.connect(DUCKDUCKGO_URL + query).get();
	}

	/**
	 * Runs the query through the html page using 'getPage()' method
	 * above. Scrapes url results
	 * @param query Search query for duckduckgo
	 * @return List of Result objects
	 */
	public List<Result> search(String query) {
		final String RESULT_CLASSNAME = "links_main links_deep result__body";

		List<Result> results = new ArrayList<>();

		try {
			Document doc = this.getPage(query);

			doc.getElementsByClass(RESULT_CLASSNAME).forEach(element -> {
				Elements aTags = element.getElementsByTag("a");

				Result result = new Result();
				result.setTitle(aTags.get(0).text());
				result.setUrl(aTags.get(0).attr("href"));
				result.setDescription(aTags.get(3).text());

				results.add(result);
			});

			return results;
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}
	}

	/**
	 * Instant answer solution, completely inefficient but easy
	 * and not redundant
	 * @param query Search query for duckduckgo
	 * @return
	 */
	public Result instantAnswerSearch(String query) {
		return this.search(query).get(0);
	}

	public static WebSearch instanceOf() {
		return new WebSearch();
	}

	@Override
	public void close() { }
}
