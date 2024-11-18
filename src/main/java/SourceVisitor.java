interface SourceVisitor
{
    void visit(FileSource fileSource);

    void visit(URISource uriSource);
}
