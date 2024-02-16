package fa;

import java.util.*;
// import java.util.Hashtable; // For transitions

import fa.dfa.DFAInterface;
// import fa.DFAState;

public class DFA implements DFAInterface{

    /* Variables */
    public LinkedHashSet<DFAState> states; // I changed from String to State
    // also thinking about ^this^ but with type as State, but that makes it hard....
    public LinkedHashSet<Character> sigma;
    public DFAState startState;
    public LinkedHashSet<DFAState> finalStates;


    /**
     * @author Caitlyn
     * Constructor for a DFA
     */
    public DFA() {
        this.startState = null;
        this.finalStates = new LinkedHashSet<DFAState>();
        this.states = new LinkedHashSet<DFAState>();
        this.sigma = new LinkedHashSet<Character>();
    }

    public DFA(DFAState start, LinkedHashSet<DFAState>finals, LinkedHashSet<DFAState> states, LinkedHashSet<Character> sigma)
    {
        this.startState = start;
        this.finalStates = finals;
        this.states = states;
        this.sigma = sigma;
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

        if (states.size() == 0)
        {
            response = true;
            DFAState newState = new DFAState(name);
            states.add(newState);
        }
        else {
            for(DFAState state : states){
                if(name.equals(state.getName())) {
                    return false;
                }
            }
            response = true;
            DFAState newState = new DFAState(name);
            states.add(newState);
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
                finalStates.add(state);
                state.finalState = true;
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
                startState = new DFAState(name);
                state.startState = true;
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
        int count = s.length();
        String []params = new String[count];
        for (int i = 0; i < count; i++) {
            params[i] = String.valueOf(s.charAt(i));
        }

        DFAState thisState = null;
        for (DFAState state : states)
        {
            if (state.startState == true)
            {
                thisState = state; // Use thisState to follow transitions
                break;
            }
        }

        if (thisState == null)
        {
            return false;
        }
        boolean validTransition = false;
        for (int i = 0; i < count; i++) { // Loop through each character of input params
            validTransition = false;
            for (Map.Entry<String, String[]> entry : thisState.transitions.entrySet()) { // Loops through each transition entry
                try {
                    for (String param : entry.getValue()) // Loops through each transition param per toState
                    {
                        if (param.equals(params[i])) // Found transition in param list
                        {
                            for (DFAState state : states) // Go through states
                            {
                                if (state.getName().equals(entry.getKey())) // find state that corresponds to our toState
                                {
                                    thisState = state; // Follow new state through valid transition
                                    thisState.startState = state.startState;
                                    thisState.finalState = state.finalState;
                                    validTransition = true;
                                    break;
                                }
                            }

                        }
                        if (validTransition)
                        {
                            break;
                        }
                    }

                } catch (NullPointerException npe)
                {
                    continue;
                }

                if (validTransition)
                {
                    break;
                }
            }
            if (i == count - 1 && (thisState.finalState == true) && validTransition) // if all params read, and ended on final state
            {
                return true;
            }
        }
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
        for(DFAState state : finalStates){
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
        if(name.equals(startState.getName())) {
            response = true;
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
        int checks = 0;
        for (DFAState state : states) {
            if (state.getName().equals(fromState))
            {
                checks++;
            }
            if (state.getName().equals(toState))
            {
                checks++;
            }
        }
        if (sigma.contains(onSymb))
        {
            checks++;
        }
        if (checks == 3)
        {
            validTransition = true;
        }
        if (validTransition)
        {   // add transition to State's transition hash table
            for (DFAState state : states) { // Find state to add transition entry to (from-state)
                if (fromState.equals(state.getName()))
                { // State found, from-state == stateObjs[i]
                    if (state.transitions.containsKey(toState)) //  FIXME  Looks for string not state
                        // toState already exists in transition table
                    {
                        // Iterate through to see if onSymb transition already exists
                        for (int j = 0; j < state.transitions.get(toState).length; j++) {
                            if (state.transitions.get(toState)[j] == null)
                            {
                                break;
                            }
                            if (state.transitions.get(toState)[j].equals(String.valueOf(onSymb)))
                            {
                                // validTransition = false;
                                return true;
                            }
                        }
                        if (validTransition) // New transition added to value
                        {
                            int index = 0;
                            for (int i = 0; i < this.sigma.size(); i++) {
                                if (state.transitions.get(toState)[i] == null)
                                {
                                    index = i;
                                    break;
                                }
                            }
                            state.transitions.get(toState)[index] = String.valueOf(onSymb); // If new symb for transition, add to value
                            if (state.startState == true)
                            {
                                startState = state;
                            }
                            if (state.finalState == true)
                            {
                                // TODO: Do we need to implement this? cause ew
                            }
                        }
                    }
                    else { // toState not already in transition table, create new entry
                        String values[] = new String[50];  // TODO: Decide max symbols on single transition
                        values[0] = String.valueOf(onSymb);
                        state.transitions.put(toState, values);
                    }
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
        //DFA swappedDfa = new DFA(this.startState, this.finalStates, this.states, this.sigma); //swappedDfa.stateObjs[i].transitions.keySet()
        DFA swappedDfa = copyDfa(this); // TODO: Figure out how to copy by value and not reference so original dfa does not change!!!!!!!!!!!!!

        for (DFAState state : swappedDfa.states) { // For each state object
                for (Map.Entry<String, String[]> entry : state.transitions.entrySet()) // Each transition set key/val pair
                {
                    String[] subValue = new String[this.sigma.size()];
                    try {
                        for (int j = 0; j < swappedDfa.sigma.size(); j++) { // Each transition per key
                            if (entry.getValue()[j].equals(String.valueOf(symb1)))
                            {
                                subValue[j] = String.valueOf(symb2);
                            }
                            else if (entry.getValue()[j].equals(String.valueOf(symb2)))
                            {
                                subValue[j] = String.valueOf(symb1);
                            }
                            else {
                                subValue[j] = entry.getValue()[j];
                            }
                        }
                    } catch (NullPointerException npe)
                    {

                    }
                    state.transitions.replace(entry.getKey(), subValue); // TODO: max array size now set, but could change later....
                }
        }
        return swappedDfa;
    }

    private DFA copyDfa(DFA dfa)
    {
        DFA newDfa = new DFA();

        LinkedHashSet<DFAState> newStates = new LinkedHashSet<DFAState>();
        for (DFAState state : dfa.states) // copy each state
        {
            DFAState addState = new DFAState(state.getName());
            Hashtable<String, String[]> newHashTable = new Hashtable<String, String[]>();
            for (Map.Entry<String, String[]> entry : state.transitions.entrySet()) // Copy each states transitions
            {
                newHashTable.put(entry.getKey(), entry.getValue());
            }
            addState.transitions = newHashTable;
            addState.startState = state.startState;
            addState.finalState = state.finalState;
            newStates.add(addState);
        }
        newDfa.states = newStates;


        LinkedHashSet<Character> sigma = new LinkedHashSet<Character>();
        for (char sig : dfa.sigma)
        {
            sigma.add(sig);
        }
        newDfa.sigma = sigma;

        LinkedHashSet<DFAState> newFinalStates = new LinkedHashSet<DFAState>();
        for (DFAState state : newDfa.states)
        {
            if (state.startState)
            {
                newDfa.startState = state;
            }
            if (state.finalState)
            {
                newFinalStates.add(state);
            }
        }
        newDfa.finalStates = newFinalStates;

        return newDfa;
    }

    /*
    for each state (in order of print table
	for each sigma (in order, or reverse order? of print table))
		for each transition entry of state
			for each String in entry.value()
				if(String == String(sigma))
					print entry.key() (the toState for transition)
     */
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
        String printFinals = "";
        String printAlpha = "";
        for(DFAState state : states){
            printStates+= state.getName() + " ";
        }
        for(Character alpha: sigma){
            printAlphabet+= alpha + " ";
        }
        for(DFAState f : finalStates){
            printFinals += f.getName() + " ";
        }
        for(Character a: sigma){
            printAlpha+= a + "   ";
        }
        System.out.println("Q = {" + " " + printStates + "}");
        System.out.println("Sigma = {" + " " + printAlphabet + "}");
        
        
        System.out.println("delta =");
        System.out.println("     " + printAlpha);
        String [] subSigma = new String[this.sigma.size()];
        int index = 0;
        for(Character c: sigma){
            subSigma[index] = String.valueOf(c);
            index++;
        }
        //TODO I think the way that these are added to the table is stumping me. I did the psuedocode however I am then crashing it.

        for(DFAState s : states){  //Each state in the state linked hashset
            System.out.print(" " + s.getName() + "   ");
            for(Map.Entry<String, String[]> entry : s.transitions.entrySet()) {
                try{
                    for (int i = 0; i < subSigma.length; i++) { // Each transition
                        // if(subSigma[i].equals(entry.getValue()[i])){
                        //     System.out.print(entry.getKey() + "   ");
                        // }
                       String c = "";
                       c = entry.getValue()[i];
                       if(c != null){
                        System.out.print(entry.getKey() + "   ");
                       }
                    }
                }
                catch (NullPointerException npe)
                {
                    break;
                }
            }
            System.out.println();
        }
        System.out.println("q0 = " + startState);
        System.out.println("F = {" + " " + printFinals + "}");
        return "";
    }
    
}
