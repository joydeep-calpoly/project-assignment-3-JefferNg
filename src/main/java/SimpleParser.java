import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

class SimpleParser implements Parser<Article>, Converter<Article>
{
    private final DataSource dataSource;
    private final Validator validator;

    SimpleParser(DataSource dataSource, Validator validator)
    {
        this.dataSource = dataSource;
        this.validator = validator;
    }

    /**
     * convert to an Article object
     *
     * @param mapper ObjectMapper that reads value from the file into an Article
     * @return an Article object containing data from parsed json
     * @throws IOException if mapper fails to read value
     */
    @Override
    public Article convert(ObjectMapper mapper) throws IOException
    {
        try (InputStream inputStream = dataSource.getInputStream())
        {
            return mapper.readValue(inputStream, Article.class);
        }
    }

    /**
     * parses article and validates
     *
     * @param article the article used to validate
     * @param logger where invalid articles will be logged
     * @return list of valid articles
     */
    @Override
    public List<Article> parse(Article article, Logger logger)
    {
        return validator.validate(List.of(article), logger);
    }

}
