package fa;

import java.util.LinkedHashSet;
import java.util.Hashtable; // For transitions
import java.util.Map;
import java.util.Set; // DFA elements that are sets, e.g., set of states Q, must be implemented

import fa.dfa.DFAInterface;
import fa.DFAState;

public class DFA implements DFAInterface{

    /* Variables */
    public LinkedHashSet<DFAState> states; // I changed from String to State
    // also thinking about ^this^ but with type as State, but that makes it hard....
    DFAState stateObjs[]; //TODO we may want to change this to just being the LinkedHashSets, but can ask for clarifiction
    // Allows State obj access ^, and to access states in order created
    public LinkedHashSet<Character> sigma;
    public LinkedHashSet<DFAState> startState;
    public LinkedHashSet<DFAState> finalState;


    /**
     * @author Caitlyn
     * Constructor for a DFA
     */
    public DFA() {
        this.startState = new LinkedHashSet<DFAState>();
        this.finalState = new LinkedHashSet<DFAState>();
        this.states = new LinkedHashSet<DFAState>();
        this.sigma = new LinkedHashSet<Character>();
        
    }

    /**
     * @author Caityln & Olivia
     * Adds a a state to the FA instance
     * @param name is the label of the state
     * @return boolean whether the new state is created (true) or not created as it already exists (false)
     */
    @Override
    public boolean addState(String name) {
        boolean response = false;
        for(DFAState state : states){
            if(name.equals(state.getName())) {
                response = false;
                return response;
            }
            response = true;
            DFAState newState = new DFAState(name);
            states.add(newState);
            stateObjs[stateObjs.length] = new DFAState(name);
        }
        
        return response;
    }

    /**
     * @author Caitlyn
	 * Marks an existing state as an accepting state
	 * @param name is the label of the state
	 * @return true if successful and false if no state with such name exists
	 */
    @Override
    public boolean setFinal(String name) {
        boolean response = false;
        for(DFAState state : states){
            if(name.equals(state.getName())) { 
                response = true;
                finalState.add(state);
            }
        }
        return response;
    }

    /**
     * @author Caitlyn
	 * Adds the initial state to the DFA instance
	 * @param name is the label of the start state
	 * @return true if successful and false if no state with such name exists
	 */
    @Override
    public boolean setStart(String name) {
        boolean response = false;
        for(DFAState state : states){
            if(name.equals(state.getName())) { 
                response = true;
                startState.add(state);
            }
        }
        return response;
    }

    /**
     * @author Caitlyn and Olivia
	 * Adds a symbol to Sigma
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
     * @return true if s in the language of the DFA and false otherwise
     */
    @Override
    public boolean accepts(String s) {
        return false;
    }

    /**
     * @author Olivia
	 * Getter for Sigma
	 * @return the alphabet of FA
	 */
    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    /**
     * @Caitlyn
	 * Returns state with the given name, or null if none exists
	 * @param name of a state
	 * @return state object or null
	 */
    @Override
    public State getState(String name) {
        DFAState stateChoosen = null;
        for(DFAState state : states){
            if(name.equals(state.getName())){
                stateChoosen = state;
            }
        }
        return stateChoosen;
    }

    /**
     * @Caitlyn
     * Determines if a state with a given name is final
	 * @param name the name of the state
	 * @return true if a state with that name exists and it is final
	 */
    @Override
    public boolean isFinal(String name) {
        boolean response = false;
        for(DFAState state : finalState){
            if(name.equals(state.getName())) { 
                response = true;
            }
        }
        return response;
    }

    /**
     * @author Caitlyn
	 * Determines if a state with name is final
	 * @param name the name of the state
	 * @return true if a state with that name exists and it is the start state
	 */
    @Override
    public boolean isStart(String name) {
        boolean response = false;
        for(DFAState state : startState){
            if(name.equals(state.getName())) { 
                response = true;
            }
        }
        return response;
    }

    /**
     * @author Olivia Hill
     * Adds the transition to the DFA's delta data structure
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

            for (DFAState state : states) { // Find state to add transition entry to (from-state)
                if (fromState == state.getName())
                { // State found, from-state == stateObjs[i]
                    if (state.transitions.containsKey(toState)) // toState already exists in transition table
                    {
                        // Iterate through to see if onSymb transition already exists
                        for (int j = 0; j < state.transitions.get(toState).length; j++) {
                            if (state.transitions.get(toState)[j] == String.valueOf(onSymb))
                            {
                                validTransition = false;
                            }
                        }
                        if (validTransition) // New transition added to value
                        {
                            state.transitions.get(toState)[state.transitions.get(toState).length] = String.valueOf(onSymb); // If new symb for transition, add to value
                        }
                    }
                    else { // toState not already in transition table, create new entry
                        String values[] = new String[50];  // TODO: Decide max symbols on single transition
                        values[0] = String.valueOf(onSymb);
                        state.transitions.put(toState, values);
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
     * @return a copy of this DFA
     */
    @Override
    public DFA swap(char symb1, char symb2) {
        DFA swappedDfa = this; //swappedDfa.stateObjs[i].transitions.keySet()
        for (DFAState state : swappedDfa.states) { // For each state object
                for (String key : state.transitions.keySet()) // Each transition set key/val pair
                {
                    String[] subValue = new String[this.sigma.size()];
                    for (int j = 0; j < state.transitions.get(key).length; j++) { // Each transition per key
                        if (state.transitions.get(key)[j] == String.valueOf(symb1))
                        {
                            subValue[j] = String.valueOf(symb2);
                        }
                        else if (state.transitions.get(key)[j] == String.valueOf(symb2))
                        {
                            subValue[j] = String.valueOf(symb1);
                        }
                        else {
                            subValue[j] = state.transitions.get(key)[j];
                        }
                    }
                    state.transitions.replace(key, subValue); // TODO: max array size now set, but could change later....
                }
        }
        return swappedDfa;
    }


    /**
     * @author Caitlyn
	 * Construct the textual representation of the DFA, for example
	 * A simple two state DFA
	 * Q = { a b }
	 * Sigma = { 0 1 }
	 * delta =
	 *		0	1	
	 *	a	a	b	
	 *	b	a	b	
	 * q0 = a
	 * F = { b }
	 * 
	 * The order of the states and the alphabet is the order
	 * in which they were instantiated in the DFA.
	 * @return String representation of the DFA
	 */
    @Override
    public String toString()
    {
        // The states and the symbols must appear in
        // the same order as they added to a DFA in a test case.
        String printStates = "";
        String printAlphabet = "";
        for(DFAState state : states){
            printStates+= state.getName() + " ";
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
