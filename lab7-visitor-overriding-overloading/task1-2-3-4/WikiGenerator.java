import java.util.List;

/**
 * Uses visitors to parse documents and provide dokuwiki and markdown outputs.
 */
public class WikiGenerator {

    private final List<TextSegment> textSegments;

    public WikiGenerator(List<TextSegment> textSegments) {
        this.textSegments = textSegments;
    }

    public StringBuilder getDokuWikiDocument() {
        DokuWikiVisitor dokuWiki = new DokuWikiVisitor();
        for (TextSegment textSegment : this.textSegments) {
            textSegment.accept(dokuWiki);
        }

        return dokuWiki.getDocument();
    }

    public StringBuilder getMarkdownDocument() {
        MarkdownVisitor markdown = new MarkdownVisitor();
        for (TextSegment textSegment : this.textSegments) {
            textSegment.accept(markdown);
        }

        return markdown.getDocument();
    }
}