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
    	
    	if(originalName == null || originalName.isEmpty()){
    		throw new IllegalArgumentException("file's name is empty");
    	}
    	if(knownNames.isEmpty()){
    		return originalName;
    	}
   		
    	String name = getFileName(originalName);
   		String extention = getFileExtension(originalName);
   		int number=0;
   		String newName = originalName;
   		for(String file:knownNames){
   			String fileName = getFileName(file);
   			String fileExtention = getFileExtension(file);
   			if(name.equals(fileName) && extention.equals(fileExtention)){
   				if(!file.equals(originalName)){
   					int filenumber = getFileNumber(file, originalName);
   					number=(filenumber>number)? (filenumber+1): number;
   				}
   				else{
   					number=1;
   				}
   				newName = String.format("%s (%d).%s", name, number, extention);
   			}
   		}
   		return newName;
    }

    public static int getFileNumber(String filename, String originalname){
    	int number=0;
    	try{
    		number=Integer.parseInt(filename.substring(filename.lastIndexOf("(")+1, filename.lastIndexOf(")")));
    	}
    	catch (Exception e){
    		return 0;
    	}
		return number;
    }

    public static String getFileExtension(String filename){
    	String name="";
    	try{
    		name = filename.substring(filename.lastIndexOf(".")+1 );
    	}
    	catch (Exception e){
    		return "";
    	}
    	return name;
    }
    
    public static String getFileName(String filename){
    	String name = "";
    	try{
    		if(filename.lastIndexOf("(")==-1){
    			name = filename.substring(0, filename.lastIndexOf("."));
    		}
    		else{
    			name = filename.substring(0, filename.lastIndexOf(" ("));
    		}
    	}
    	catch (Exception e){
    		return "";
    	}
    	return name;
    }
    
    private Filenames() {
    }
}
