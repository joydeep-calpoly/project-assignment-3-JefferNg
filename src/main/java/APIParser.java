import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

class APIParser implements Parser<JSON>, Converter<JSON>
{
    private final DataSource dataSource;
    private final Validator validator;

    APIParser(DataSource dataSource, Validator validator)
    {
        this.dataSource = dataSource;
        this.validator = validator;
    }

    /**
     * convert to JSON object
     *
     * @param mapper the ObjectMapper that reads value from the json file into a JSON object
     * @return a JSON object containing data from the parsed file
     * @throws IOException if the mapper fails to read value
     */
    @Override
    public JSON convert(ObjectMapper mapper) throws IOException {
        try (InputStream inputStream = dataSource.getInputStream())
        {
            return mapper.readValue(inputStream, JSON.class);
        }
    }

    /**
     * parses the json file and prints all valid articles while logging invalid articles
     *
     * @param json the JSON object used for parsing
     * @param logger where invalid articles will be logged
     * @return a list of valid articles
     */
    @Override
    public List<Article> parse(JSON json, Logger logger)
    {
        return validator.validate(json.getArticles(), logger);
    }


}
