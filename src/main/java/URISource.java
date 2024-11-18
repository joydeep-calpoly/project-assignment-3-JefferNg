class URISource implements Source
{
    private final String uri;

    URISource(String uri)
    {
        this.uri = uri;
    }

    public String getUri()
    {
        return uri;
    }

    /**
     * calls the visitor function that takes a URISource as its parameter
     *
     * @param visitor the visitor in which the visit function is invoked
     */
    public void accept(SourceVisitor visitor)
    {
        visitor.visit(this);
    }
}
