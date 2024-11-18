import java.util.List;
import java.util.logging.Logger;

interface Validator
{
    List<Article> validate(List<Article> articles, Logger logger);
}
