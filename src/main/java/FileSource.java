class FileSource implements Source
{
    private final String path;

    FileSource(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }

    /**
     * calls the visitor function that takes a FileSource as its parameter
     *
     * @param visitor the visitor in which the visit function is invoked
     */
    public void accept(SourceVisitor visitor)
    {
        visitor.visit(this);
    }
}
