package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// TODO: write a JavaDoc for the class
// The Label class store tha label name and its address in maps
// Label class can be get the address of lable and set the label name

/**
 *
 * @author ...
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.
                               // Check the duplicate label in Translator function readAndTranslate() function inside
                               // If duplicate the show error message otherwise add the label
		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
                               if(label == null)  // Check if label is null then return 0 otherwise return key value
                                   return 0;
                               else
		    return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		return labels.toString()+" "+labels.getClass();
	}

	// TODO: Implement equals and hashCode (needed in class Machine).
                // equals () function comapre object of Machine class Object
                @Override
                public boolean equals(Object o)
                {
                    if(o instanceof Machine)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                
                
                @Override
                public int hashCode()
                {
                    return Objects.hash(labels);
                }
	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
