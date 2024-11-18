import java.io.IOException;
import java.io.InputStream;

interface DataSource
{
    InputStream getInputStream() throws IOException;
}
