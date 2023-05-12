public class MarkdownVisitor implements DocumentVisitor {
    StringBuilder document;

    public MarkdownVisitor() {
        this.document = new StringBuilder();
    }

    @Override
    public void visit(ItalicTextSegment italicTextSegment) {
        document.append("*").append(italicTextSegment.getContent()).append("*");
    }

    @Override
    public void visit(BoldTextSegment boldTextSegment) {
        document.append("**").append(boldTextSegment.getContent()).append("**");
    }

    @Override
    public void visit(UrlSegment urlSegment) {
        document.append("[").append(urlSegment.getDescription()).append("]");
        document.append("(").append(urlSegment.getUrl()).append(")");
    }

    @Override
    public void visit(PlainTextSegment plainTextSegment) {
        document.append(plainTextSegment.getContent());
    }

    @Override
    public StringBuilder getDocument() {
        return this.document;
    }

    /*
    @Override
    public void visit(TextSegment textSegment) {
        if (textSegment instanceof ItalicTextSegment) {
            document.append("_" + textSegment.getContent() + "_");
        } else if (textSegment instanceof BoldTextSegment) {
            document.append("__" + textSegment.getContent() + "__");
        } else if (textSegment instanceof UrlSegment) {
            document.append("[" + ((UrlSegment) textSegment).getDescription() + "]");
            document.append("(" + ((UrlSegment) textSegment).getUrl() + ")");
        } else {
            document.append(textSegment.getContent());
        }
    }
    */
}