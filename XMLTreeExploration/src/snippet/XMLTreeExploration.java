package snippet;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {

        int number = xt.numberOfChildren() / 2;

        out.println("The middle child's label is " + xt.child(number));

        if (xt.child(number).isTag()) {
            out.println("The middle child's node is a tag.");
            out.println("The middle child has "
                    + xt.child(number).numberOfChildren()
                    + " number of children.");
        } else {
            out.println("The middle child's node is text.");
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        XMLTree xml = new XMLTree1(
                "http://xml.weather.yahoo.com/forecastrss/43210.xml");

        xml.display();

        //out.print(xml.toString());

        if (xml.isTag()) {
            out.println(xml.label() + " is a tag.");
        } else {
            out.println(xml.label() + " is text.");
        }

        XMLTree channel = xml.child(0);

        out.println("The number of children of the node channel is: "
                + channel.numberOfChildren());

        XMLTree title = channel.child(0);
        XMLTree titleText = title.child(0);

        out.println("The text child under the node title is: "
                + titleText.label());

        out.println("Once again the, text child under the title node is: "
                + xml.child(0).child(0).child(0).label());

        XMLTree astronomy = channel.child(10);

        String sunset = "sunset";
        String midday = "midday";
        String sunrise = "sunrise";

        if (astronomy.hasAttribute(sunset)) {

            out.println("Yes, the astronomy node has the attribute sunset.");

        } else {
            out.println("No, the astronomy node does not have the attribute sunset.");
        }
        if (astronomy.hasAttribute(midday)) {

            out.println("Yes, the astronomy node has the attribute midday.");

        } else {
            out.println("No, the astronomy node does not have the attribute midday.");
        }

        out.println("The value of attribute sunset: "
                + astronomy.attributeValue(sunset));
        out.println("The value of attribute sunrise: "
                + astronomy.attributeValue(sunrise));

        printMiddleNode(channel, out);

        in.close();
        out.close();
    }

}
