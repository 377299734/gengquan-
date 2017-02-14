
/**
 * Model class.
 *
 * @author Bruce W. Weide
 * @author Paolo Bucci
 */
public final class AppendUndoModel1 implements AppendUndoModel {

    /**
     * Model variables.
     */
    String input;
    String output;

    /**
     * Default constructor.
     */
    public AppendUndoModel1() {
        /*
         * Initialize model; both variables start as empty strings
         */

    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String input() {
        return this.input;
    }

    @Override
    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String output() {
        return this.output;
    }

}
