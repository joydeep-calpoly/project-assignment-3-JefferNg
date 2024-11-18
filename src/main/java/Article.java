import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Article
{
    private final Source source;
    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;
    private final String publishedAt;
    private final String content;

    /**
     *  Creates an Article object that parsed the essential information of the article.
     *
     * @param source a tuple of strings containing the id and name of the source of the article
     * @param author the author of the article
     * @param title the title of the article
     * @param description the description of the article
     * @param url the source of the article
     * @param urlToImage the header image of the article
     * @param publishedAt the date when the article was published
     * @param content the content of the article
     */
    @JsonCreator
    Article(@JsonProperty("source") Source source, @JsonProperty("author") String author, @JsonProperty("title") String title, @JsonProperty("description") String description, @JsonProperty("url") String url, @JsonProperty("urlToImage") String urlToImage, @JsonProperty("publishedAt") String publishedAt, @JsonProperty("content") String content)
    {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Source getSource()
    {
        return source;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public String getUrl()
    {
        return url;
    }

    public String getUrlToImage()
    {
        return urlToImage;
    }

    public String getPublishedAt()
    {
        return publishedAt;
    }

    public String getContent()
    {
        return content;
    }

    static class Source
    {
        private final String id;
        private final String name;

        /**
         * Creates a Source object that parsed the tuple of strings containing the id and the name.
         *
         * @param id the id of the source of the article
         * @param name the name of the source of the article
         */
        @JsonCreator
        Source(@JsonProperty("id") String id, @JsonProperty("name") String name)
        {
            this.id = id;
            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        public String getId()
        {
            return id;
        }
    }
}
