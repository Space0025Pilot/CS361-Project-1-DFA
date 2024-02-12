package fa;

public abstract class State {

	/**
	 * The state label.
	 * It should be a unique name set by 
	 * the concrete class constructor.
	 * @author elenasherman
	 */
	private String name;

	public State(){

	}
	
	/**
	 * All concrete consturctors must
	 * invoke this one as super(name).
	 * This way name instance variable is 
	 * correctly set.
	 */
	public void State(String name) {
		this.name = name;
	}
	
	/**
	 * getter for the string label
	 * @return returns the state label.
	 */
	public String getName(){
		return name;
	}

	/**
	 * Simply provides the string of the name
	 * @return s string representation of the object
	 */
	@Override
	public String toString(){
		return name;
	}
	
	
}
