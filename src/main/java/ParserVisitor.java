import java.net.URI;
import java.net.URISyntaxException;

class ParserVisitor implements SourceVisitor
{
    private final Format format;
    private Parser<?> parser;
    private final Validator validator;

    ParserVisitor(Format format, Validator validator)
    {
        this.format = format;
        this.validator = validator;
    }

    /**
     * creates a parser based on the given format for the file source
     *
     * @param fileSource the source of data
     */
    @Override
    public void visit(FileSource fileSource)
    {
        switch (format)
        {
            case NEWSAPI -> parser = new APIParser(new FileDataSource(fileSource.getPath()), validator);
            case SIMPLE -> parser = new SimpleParser(new FileDataSource(fileSource.getPath()), validator);
            default -> throw new IllegalArgumentException("Unsupported format for file source");
        }
    }

    /**
     * creates the API parser for the uri source
     *
     * @param uriSource the source of data
     */
    @Override
    public void visit(URISource uriSource)
    {
        if (format == Format.NEWSAPI)
        {
            try
            {
                parser = new APIParser(new URIDataSource(new URI(uriSource.getUri())), validator);
            } catch (URISyntaxException e)
            {
                throw new IllegalArgumentException("URI source not valid");
            }
        }
        else
        {
            throw new IllegalArgumentException("Simple not supported for URI");
        }
    }

    public Parser<?> getParser()
    {
        return parser;
    }
}
