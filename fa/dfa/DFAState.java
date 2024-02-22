package fa.dfa;

import fa.State;

import java.util.Hashtable;

public class DFAState extends State {
    // Variables
    public Hashtable<String, String[]> transitions;
    boolean startState;
    boolean finalState;
    /**
     * @author Caitlyn
     * DFA State constructor that calls the super implementation to set the name
     * @param name
     */
    public DFAState(String name) {
        super.State(name);
        this.transitions = new Hashtable<String, String[]>();
        this.startState = false;
        this.finalState = false;
    }


}
