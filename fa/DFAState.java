package fa;

import java.util.Hashtable;

public class DFAState extends State{
    // Variables
    public Hashtable<String, String[]> transitions; // Would make K char, but no primitive type allowed...
    // Will need to convert between String and char for key value
    //TODO for this we may want to use char and DFAState for K, V respectively
    /**
     * @author Caitlyn
     * DFA State constructor that calls the super implementation to set the name
     * @param name
     */
    public DFAState(String name) {
        super.State(name);
    }
}
