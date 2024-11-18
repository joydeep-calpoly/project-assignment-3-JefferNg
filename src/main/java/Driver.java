import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Driver
{
    public static void main(String[] args) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        Logger logger = Logger.getLogger(Driver.class.getName());
        FileHandler fileHandler = new FileHandler("badArticles.log");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        Validator validator = new ArticleValidator();
        ArticlePrinter printer = new ArticlePrinter();

        processSource(new FileSource("project_3/inputs/newsapi.txt"), Format.NEWSAPI, validator, mapper, logger, printer);
        processSource(new FileSource("project_3/inputs/simple.txt"), Format.SIMPLE, validator, mapper, logger, printer);

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties"))
        {
            properties.load(input);
        }

        String apiKey = properties.getProperty("api.key");
        if (apiKey == null)
        {
            System.err.println("API key not found in config.properties");
            return;
        }
        StringBuilder url = new StringBuilder("https://newsapi.org/v2/top-headlines?country=us&apiKey=");
        url.append(apiKey);
        processSource(new URISource(url.toString()), Format.NEWSAPI, validator, mapper, logger, printer);
    }

    private static <T> void processSource(
            Source source,
            Format format,
            Validator validator,
            ObjectMapper mapper,
            Logger logger,
            ArticlePrinter printer
    )
    {
        ParserVisitor visitor = new ParserVisitor(format, validator);
        source.accept(visitor);

        Parser<T> parser = (Parser<T>) visitor.getParser();
        Converter<T> converter = (Converter<T>) parser;

        try
        {
            T data = converter.convert(mapper);
            List<Article> articles = parser.parse(data, logger);
            printer.print(articles);
        }
        catch (IOException e)
        {
            System.err.println("Failed to process source: " + e.getMessage());
        }
    }
}
