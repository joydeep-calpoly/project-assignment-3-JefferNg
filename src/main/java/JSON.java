import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class JSON
{
    private final String status;
    private final int totalResults;
    private final List<Article> articles;

    /**
     * Returns a JSON object that parsed the status, totalResults, and article values
     * from the input json file.
     *
     * @param status the status value of the json
     * @param totalResults the total number of articles in the json
     * @param article a list of articles
     */
    @JsonCreator
    JSON(@JsonProperty("status") String status, @JsonProperty("totalResults") int totalResults, @JsonProperty("article") List<Article> article)
    {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = article;
    }

    public String getStatus()
    {
        return status;
    }

    public int getTotalResults()
    {
        return totalResults;
    }

    public List<Article> getArticles()
    {
        return articles;
    }
}
