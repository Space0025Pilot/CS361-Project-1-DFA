package fa;

import java.util.HashSet;
import java.util.Hashtable; // For transitions
import java.util.Set; // DFA elements that are sets, e.g., set of states Q, must be implemented

import fa.dfa.DFAInterface;

public class DFA implements DFAInterface{

    /* Variables */
    public HashSet<String> states; // I changed from String to State
    // also thinking about ^this^ but with type as State, but that makes it hard....
    public HashSet<Character> sigma;
    private String startState;
    private String finalState;


    /**
     * Constructor
     */
    public DFA() {
        this.startState = "";
        this.finalState = "";
        states.clear(); // NullPtr exception warning?
        sigma.clear();
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
            states.add(name);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
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
     *
     * @param fromState is the label of the state where the transition starts
     * @param toState is the label of the state where the transition ends
     * @param onSymb is the symbol from the DFA's alphabet.
     * @return
     */
    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        boolean validTransition = false;
        if (states.contains(fromState) && states.contains(toState) && sigma.contains(onSymb))
        {
            validTransition = true;
            // add transition to State's transition hash table
        }
        // TODO: Check incoming transitions for if in alphabet / states
        // throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
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
        return "";
    }
    
}
