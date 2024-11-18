import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTests
{
    Validator validator = new ArticleValidator();

    /**
     * tests to ensure a file source with a newsapi format returns an API Parser
     */
    @Test
    void testAPIParserCreation()
    {
        FileSource fileSource = new FileSource("project_3/inputs/newsapi.txt");
        ParserVisitor visitor = new ParserVisitor(Format.NEWSAPI, validator);

        fileSource.accept(visitor);
        Parser<?> parser = visitor.getParser();

        assertNotNull(parser);
        assertTrue(parser instanceof APIParser);
    }

    /**
     * tests to ensure a file source with a simple format returns a simple parser
     */
    @Test
    void testSimpleParserCreation()
    {
        FileSource fileSource = new FileSource("project_3/inputs/simple.txt");
        ParserVisitor visitor = new ParserVisitor(Format.SIMPLE, validator);

        fileSource.accept(visitor);
        Parser<?> parser = visitor.getParser();

        assertNotNull(parser);
        assertTrue(parser instanceof SimpleParser);
    }

    /**
     * tests to ensure an url with a newsapi format returns an API parser
     */
    @Test
    void testURLAPIParserCreation()
    {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties"))
        {
            properties.load(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String apiKey = properties.getProperty("api.key");
        if (apiKey == null)
        {
            System.err.println("API key not found in config.properties");
            return;
        }

        StringBuilder url = new StringBuilder("https://newsapi.org/v2/top-headlines?country=us&apiKey=");
        url.append(apiKey);
        URISource fileSource = new URISource(url.toString());
        ParserVisitor visitor = new ParserVisitor(Format.NEWSAPI, validator);


        fileSource.accept(visitor);
        Parser<?> parser = visitor.getParser();

        assertNotNull(parser);
        assertTrue(parser instanceof APIParser);
    }
}
