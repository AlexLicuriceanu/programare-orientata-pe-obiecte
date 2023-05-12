public class ItalicTextSegment extends TextSegment {

    public ItalicTextSegment(String content) {
        super(content);
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }

}