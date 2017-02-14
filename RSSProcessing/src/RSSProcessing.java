import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Put your name here
 *
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int end = -1;

        int mainCount = 0;
        while (xml.numberOfChildren() > mainCount) {
            if (xml.child(mainCount).label().equals(tag)) {
                end = mainCount;
            }
            mainCount++;
        }
        return end;
    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires <pre>
     * [the label of the root of item is an <item> tag] and out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the title (or description) and
     * link]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        int k = 0;
        while (item.numberOfChildren() > k) {
            if ((item.child(k).label()).equals("title")) {
                out.println("Title: " + item.child(k).child(0));
            }
            if ((item.child(k).label()).equals("link")) {
                out.println("Link: " + item.child(k).child(0));
            }

            k++;
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open I/O streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Input the source URL.
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();
        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);
        /*
         * Extract <channel> element.
         */
        XMLTree channel = xml.child(0);

        String title = "title";
        String description = "description";
        String link = "link";

        if (getChildElement(channel, title) != -1) {
            out.println("Title: "
                    + channel.child(getChildElement(channel, title)).child(0));
        }
        if (getChildElement(channel, description) != -1) {
            out.println("Description: "
                    + channel.child(getChildElement(channel, description))
                            .child(0));
        }
        if (getChildElement(channel, link) != -1) {
            out.println("Link: "
                    + channel.child(getChildElement(channel, link)).child(0));
        }

        int l = 0;
        int j = 0;
        while (channel.numberOfChildren() > l) {
            if (channel.child(l).label().equals("item")) {
                while (channel.child(j).numberOfChildren() > j) {
                    processItem(channel.child(l), out);
                    j++;
                }
            }
            l++;
        }

        out.println();

        in.close();
        out.close();
    }

}
