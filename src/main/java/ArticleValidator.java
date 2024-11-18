import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ArticleValidator implements Validator
{
    /**
     * validates whether an article contains enough information to be considered valid
     *
     * @param articlesJSON list of articles parsed from json
     * @param logger logger used to log bad articles
     * @return list of valid articles
     */
    @Override
    public List<Article> validate(List<Article> articlesJSON, Logger logger)
    {
        List<Article> goodArticles = new ArrayList<>();
        for (Article article : articlesJSON)
        {
            StringBuilder missingFields = new StringBuilder();

            if (article.getTitle() == null)
            {
                missingFields.append("title, ");
            }
            if (article.getDescription() == null)
            {
                missingFields.append("description, ");
            }
            if (article.getPublishedAt() == null)
            {
                missingFields.append("published date, ");
            }
            if (article.getUrl() == null)
            {
                missingFields.append("URL, ");
            }

            if (missingFields.length() == 0)
            {
                goodArticles.add(article);
            }
            else
            {
                // remove the trailing comma and space
                missingFields.setLength(missingFields.length() - 2);
                logger.log(Level.WARNING,"The following fields are missing from the article: {0}", missingFields);
            }
        }
        return goodArticles;
    }
}
