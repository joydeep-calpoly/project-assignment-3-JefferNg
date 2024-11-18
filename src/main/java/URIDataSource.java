import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

class URIDataSource implements DataSource
{
    private final URI uri;

    URIDataSource(URI uri)
    {
        this.uri = uri;
    }

    /**
     * convert data source from URI into an input stream
     *
     * @return an input stream of the data
     * @throws IOException if the source cannot be found
     */
    @Override
    public InputStream getInputStream() throws IOException
    {
        return uri.toURL().openStream();
    }
}
