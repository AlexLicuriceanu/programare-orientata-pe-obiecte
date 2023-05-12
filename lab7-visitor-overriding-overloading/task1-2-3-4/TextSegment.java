/**
 * Represents a text segment of a document that needs to be parsed.
 */
public abstract class TextSegment {
    private final String content;

    public TextSegment(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public abstract void accept(DocumentVisitor visitor);

    /*
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
     */
}