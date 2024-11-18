import java.util.List;

class ArticlePrinter
{
    /**
     * prints valid Articles parsed from the JSON object
     *
     * @param articles a list of valid Articles
     */
    public void print(List<Article> articles)
    {
        for (Article a: articles)
        {
            System.out.println("Title: " + a.getTitle());
            System.out.println("Description: " + a.getDescription());
            System.out.println("Published At: " + a.getPublishedAt());
            System.out.println("Url: " + a.getUrl());
            System.out.println();
        }
    }
}
