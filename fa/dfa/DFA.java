package fa.dfa;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fa.State;

public class DFA implements DFAInterface{

    /* Variables */
    public HashSet<String> states;
    public HashSet<Character> sigma;
    // public Map<keys,DFA> transitions;
    private String startState;
    private String finalState;


    /* Constructor */
    public DFA() {
        this.startState = "";
        this.finalState = "";
    } 

    @Override
    public boolean addState(String name) {
        boolean response = false;
        if(!states.contains(name)) {
            response = true;
            states.add(name);
        }
        return response;
        
    }

    @Override
    public boolean setFinal(String name) {
        boolean response = false;
        if(states.contains(name)){
            response = true;
            this.finalState = name;
        }
        return response;
        
    }

    @Override
    public boolean setStart(String name) {
        boolean response = false;
        if(states.contains(name)){
            response = true;
            this.startState = name;
        }
        return response;
    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSigma'");
    }

    @Override
    public State getState(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }

    @Override
    public boolean isFinal(String name) {
        boolean response = false;
        if(name.equals(finalState)){
            response = true;
        }
        return response;
    }

    @Override
    public boolean isStart(String name) {
        boolean response = false;
        if(name.equals(startState)){
            response = true;
        }
        return response;
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }
    
}
