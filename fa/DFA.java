package fa;

import java.util.HashSet;
import java.util.Hashtable; // For transitions
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
     *
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
     *
     * @param s - the input string
     * @return
     */
    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    /**
     *
     * @return
     */
    @Override
    public Set<Character> getSigma() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSigma'");
    }

    /**
     *
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
     *
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
     *
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
     * @return boolean is if the transition was valid and added
     */
    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        boolean validTransition = false;
        if (states.contains(fromState) && states.contains(toState) && sigma.contains(onSymb))
        {
            validTransition = true;
            // add transition to State's transition hash table
            for (int i = 0; i < states.size(); i++) {
                if (fromState == stateObjs[i].getName())
                {
                    stateObjs[i].transitions.put(String.valueOf(onSymb), toState);
                }
            }
        }
        return false;
    }

    /**
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }

    /**
     *
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
