package hrytsenko;

import java.util.List;

/**
 * Utilities for working with filenames.
 */
public final class Filenames {

    /**
     * Generates the unique name to avoid duplication with the known names.
     * 
     * <p>
     * To generate unique name, we add serial number to the end of name.
     * 
     * For example: "logo.png", "logo (1).png" and so on.
     * 
     * @param originalName
     *            the original name.
     * @param knownNames
     *            the list of known names.
     * 
     * @return the generated unique name.
     */
    public static String generateUniqueName(String originalName, List<String> knownNames) {
    	if(!knownNames.contains(originalName)){
    		return originalName;
    	}
    	else{
    		int n=1;
    		int point = originalName.indexOf(".");
    		String newName = originalName.substring(0, point)+" ("+n+")"+originalName.substring(point, originalName.length());

    		for(String name:knownNames){
    			if(name.substring(0, point).equals(originalName.substring(0, point)) 
    					&& name.substring(name.length()-(originalName.length()-point), name.length()).equals(originalName.substring(point, originalName.length()))
    					&& !name.equals(originalName)){
    	    		int number = Integer.parseInt(name.substring(point+2, name.length()-(originalName.length()-point)-1));
    	    		n=(number>n)? (number+1): n;
    				newName = originalName.substring(0, point)+" ("+n+")"+originalName.substring(point, originalName.length());
    			}
    		} 		
    		return newName;
    	}
    }

    private Filenames() {
    }

}
