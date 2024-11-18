import java.util.List;
import java.util.logging.Logger;

interface Parser<T>
{
    List<Article> parse(T item, Logger logger);
}
