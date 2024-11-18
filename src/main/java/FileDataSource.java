import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class FileDataSource implements DataSource
{
    private final File file;

    FileDataSource(String pathname)
    {
        file = new File(pathname);
    }

    /**
     * convert data source from file into an input stream
     *
     * @return an input stream of the data
     * @throws IOException if the source cannot be found
     */
    @Override
    public InputStream getInputStream() throws IOException
    {
        return new FileInputStream(file);
    }
}
