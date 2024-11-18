import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

interface Converter<T>
{
    T convert(ObjectMapper mapper) throws IOException;
}
