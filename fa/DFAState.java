package fa;

import java.util.Hashtable;

public class DFAState extends State{
    // Variables
    public Hashtable<String, String> transitions; // Would make K char, but no primitive type allowed...
    // Will need to convert between String and char for key value

    /**
     * The state label.
     * It should be a unique name set by
     * the concrete class constructor.
     * @author elenasherman
     */
    private String name;

    /**
     *
     */
    public void State() {

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

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (name == o.toString())
        {
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return name;
    }


}
