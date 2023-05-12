public class DokuWikiVisitor implements DocumentVisitor {
    StringBuilder document;

    public DokuWikiVisitor() {
        this.document = new StringBuilder();
    }

    @Override
    public void visit(ItalicTextSegment italicTextSegment) {
        document.append("//").append(italicTextSegment.getContent()).append("//");
    }

    @Override
    public void visit(BoldTextSegment boldTextSegment) {
        document.append("**").append(boldTextSegment.getContent()).append("**");
    }

    @Override
    public void visit(UrlSegment urlSegment) {
        document.append("[[").append(urlSegment.getUrl()).append("|");
        document.append(urlSegment.getDescription()).append("]]");
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
            document.append("//" + textSegment.getContent() + "//");
        } else if (textSegment instanceof BoldTextSegment) {
            document.append("**" + textSegment.getContent() + "**");
        } else if (textSegment instanceof UrlSegment) {
            document.append("[[" + ((UrlSegment) textSegment).getUrl() + "|");
            document.append(((UrlSegment) textSegment).getDescription() + "]]");
        } else {
            document.append(textSegment.getContent());
        }
    }
    */
}