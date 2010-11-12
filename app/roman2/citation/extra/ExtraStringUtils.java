package extra;

public class ExtraStringUtils {

    public static String Capitalize(String str){
	    return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String AllCapsToCapitalized(String str){
	    return str.charAt(0) + str.substring(1).toLowerCase();
    }

    public static String startTag(String str){
        return "<" + str + ">" ;  
    }
    
    public static String endTag(String str){
        return "<" + str + ">" ;  
    }
    
    public static String asXMLElement(String Ident,String middle)
    {
        return startTag(Ident) + middle +endTag(Ident);
        
    }
    public static String identifierToHumanReadable(String Ident){
    	return AllCapsToCapitalized(Ident) + ": ";
    }
}
