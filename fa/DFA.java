package fa;

import java.util.HashSet;
import java.util.Hashtable; // For transitions
import java.util.Map;
import java.util.Set; // DFA elements that are sets, e.g., set of states Q, must be implemented

import fa.dfa.DFAInterface;
import fa.DFAState;

public class DFA implements DFAInterface{

    /* Variables */
    public HashSet<String> states; // I changed from String to State
    // also thinking about ^this^ but with type as State, but that makes it hard....
    DFAState stateObjs[];
    // Allows State obj access ^, and to access states in order created
    public HashSet<Character> sigma;
    private String startState;
    private String finalState;


    /**
     * Constructor
     */
    public DFA() {
        this.startState = "";
        this.finalState = "";
        
    }

    /**
     * @author Caityln & Olivia
     * @param name is the label of the state
     * @return
     */
    @Override
    public boolean addState(String name) {
        boolean response = false;
        if(!states.contains(name)) {
            response = true;
            // DFAState newState = createNewState(name);
            states.add(name);
            stateObjs[stateObjs.length] = new DFAState(name);
        }
        return response;
    }

    /**
     *
     * @param name is the label of the state
     * @return
     */
    @Override
    public boolean setFinal(String name) {
        boolean response = false;
        if(states.contains(name)){
            response = true;
            this.finalState = name;
        }
        return response;
    }

    /**
     *
     * @param name is the label of the start state
     * @return
     */
    @Override
    public boolean setStart(String name) {
        boolean response = false;
        if(states.contains(name)){
            response = true;
            this.startState = name;
        }
        return response;
    }

    /**
     *
     * @param symbol to add to the alphabet set
     */
    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    /**
     * @author Olivia Hill
     * Simulates a DFA on input s to determine
     * whether the DFA accepts s.
     * @param s - the input string
     * @return
     */
    @Override
    public boolean accepts(String s) {
        return false;
    }

    /**
     * @author Olivia
     * @return
     */
    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    /**
     * @Caitlyn
     * @param name of a state
     * @return
     */
    @Override
    public State getState(String name) {
        DFAState state;
        if(states.contains(name)){
            state = states; //TODO FIX THIS!!
        }
    }

    /**
     * @Caitlyn
     * @param name the name of the state
     * @return
     */
    @Override
    public boolean isFinal(String name) {
        boolean response = false;
        if(name.equals(finalState)){
            response = true;
        }
        return response;
    }

    /**
     * @Caitlyn
     * @param name the name of the state
     * @return
     */
    @Override
    public boolean isStart(String name) {
        boolean response = false;
        if(name.equals(startState)){
            response = true;
        }
        return response;
    }

    /**
     * @author Olivia Hill
     * @param fromState is the label of the state where the transition starts
     * @param toState is the label of the state where the transition ends
     * @param onSymb is the symbol from the DFA's alphabet.
     * @return boolean is if the transition was valid and added (returns true if already existed)
     */
    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        boolean validTransition = false;

        if (states.contains(fromState) && states.contains(toState) && sigma.contains(onSymb))
        {   // add transition to State's transition hash table
            validTransition = true;

            for (int i = 0; i < states.size(); i++) { // Find state to add transition entry to (from-state)
                if (fromState == stateObjs[i].getName())
                { // State found, from-state == stateObjs[i]
                    if (stateObjs[i].transitions.containsKey(toState)) // toState already exists in transition table
                    {
                        // Iterate through to see if onSymb transition already exists
                        for (int j = 0; j < stateObjs[i].transitions.get(toState).length; j++) {
                            if (stateObjs[i].transitions.get(toState)[j] == String.valueOf(onSymb))
                            {
                                validTransition = false;
                            }
                        }
                        if (validTransition) // New transition added to value
                        {
                            stateObjs[i].transitions.get(toState)[stateObjs[i].transitions.get(toState).length] = String.valueOf(onSymb); // If new symb for transition, add to value
                        }
                    }
                    else { // toState not already in transition table, create new entry
                        String values[] = new String[50];  // TODO: Decide max symbols on single transition
                        values[0] = String.valueOf(onSymb);
                        stateObjs[i].transitions.put(toState, values);
                    }
                    validTransition = true;
                }
            }
        }
        return validTransition;
    }

    /**
     * @Olivia Hill
     * Creates a deep copy of this DFA
     * which transitions labels are
     * swapped between symbols symb1
     * and symb2.
     * @param symb1
     * @param symb2
     * @return
     */
    @Override
    public DFA swap(char symb1, char symb2) {
        DFA swappedDfa = this;
        for (int i = 0; i < swappedDfa.stateObjs.length; i++) { // For each state object
                for (String key : swappedDfa.stateObjs[i].transitions.keySet()) // Each transition set key/val pair
                {
                    String[] subValue = new String[this.sigma.size()];
                    for (int j = 0; j < swappedDfa.stateObjs[i].transitions.get(key).length; j++) { // Each transition per key
                        if (swappedDfa.stateObjs[i].transitions.get(key)[j] == String.valueOf(symb1))
                        {
                            subValue[j] = String.valueOf(symb2);
                        }
                        else if (swappedDfa.stateObjs[i].transitions.get(key)[j] == String.valueOf(symb2))
                        {
                            subValue[j] = String.valueOf(symb1);
                        }
                        else {
                            subValue[j] = swappedDfa.stateObjs[i].transitions.get(key)[j];
                        }
                    }
                    swappedDfa.stateObjs[i].transitions.replace(key, subValue); // FIXME: max array size now set, but could change later....
                }
        }
        return swappedDfa;
    }


    /**
     * @Caitlyn
     * @return
     */
    @Override
    public String toString()
    {
        // The states and the symbols must appear in
        // the same order as they added to a DFA in a test case.
        String printStates = "";
        String printAlphabet = "";
        for(String state : states){
            printStates+= state + " ";
        }
        for(Character alpha: sigma){
            printAlphabet+= alpha + " ";
        }
        System.out.println("Q = {" + printStates + "}");
        System.out.println("Sigma = {" + printAlphabet + "}");
        System.out.println("Delta = "); //TODO stil not sure how we want transitions to go yet
        System.out.println("q0 = " + startState);
        System.out.println("F = {" + finalState + "}");
        return "";
    }
    
}
