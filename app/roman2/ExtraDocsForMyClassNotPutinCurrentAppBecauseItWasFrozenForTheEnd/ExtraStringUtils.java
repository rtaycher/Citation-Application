package citation.extra;
/**
 * Some String Manipulations usefull for our application
 */
public class ExtraStringUtils {

    public static String Capitalize(String str){
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
/**
 * @param "STRING"
 * @return "String"
 */
    public static String AllCapsToCapitalized(String str){
            return str.charAt(0) + str.substring(1).toLowerCase();
    }
/**
 * @param "str"
 * @return "<str>"
 */
    public static String startTag(String str){
        return "<" + str + ">" ;
    }
    /**
     * @param "str"
     * @return "</str>"
     */
    public static String endTag(String str){
        return "<" + str + ">" ;
    }
/**
 * 
 * @param Ident
 * @param middle
 * @return <Ident>middle</Ident>
 */

    public static String asXMLElement(String Ident,String middle)
    {
    	System.out.println("ss");
        return startTag(Ident) + middle +endTag(Ident);
/**
 * 
 */
    }
    public static String identifierToHumanReadable(String Ident){
        return AllCapsToCapitalized(Ident) + ": ";
    }
/*
 * Java ME/blackberry strings leave out a lot of the useful functions
 * such as split. So this is an extremely simple substitute for this project(not meant to be a general solution.
 * Assumes neither start nor end character are splitters and that splitters don't occur in a row
 * @param stringToSplit
 * @param splitter=charecter used to split
 * @return an array of string split by splitter char or null if stringToSplit is null
 */
        public static String[] split(String stringToSplit, char splitter) {
                if(stringToSplit==null)
                        return null;
                int i;
                int numChars=stringToSplit.length();//caches length of full string
                char [] charArrayToSplit=stringToSplit.toCharArray();
                int [] splitterLocations=new int[numChars];//sets up array to be used for recording where splits should happen
                splitterLocations[0]=0;
        STuple [] stringSplitingArray=new STuple[numChars];
        int numOfSplitTupples=0;
                int numOfSplitters=0;//counts how many splitters there are
                String [] ArrayofSplitStrings;
                int lastSplitter=stringToSplit.lastIndexOf(splitter);
                int lastsplit=0;
                int begin = 0;
                int end = 0;
                char [] allSplitters=new char[numChars];
                if(stringToSplit.indexOf(splitter)==-1)
                    {
                        ArrayofSplitStrings=new String[1];
                        ArrayofSplitStrings[0]=stringToSplit;
                        return ArrayofSplitStrings;//no splitters in string
                    }
                for(i=0;i<numChars;i++){
                        allSplitters[i]=splitter;
                }
                if((new String(allSplitters).equals(stringToSplit)))//do we have a string that only has splitters then return null
                        return null;
        while((end=stringToSplit.indexOf(' ',end))!=-1){

            if(begin<numChars-1){
                                stringSplitingArray[numOfSplitTupples]=new STuple(new Integer(begin),new Integer(end));
                                begin=end+1;
                                numOfSplitters++;
                    }
            else
                break;
                }
                ArrayofSplitStrings= new String[numOfSplitters];
                for(i=0;i<=numOfSplitters;i++)
                        ArrayofSplitStrings[i]=stringToSplit.substring(((Integer)stringSplitingArray[i].First()).intValue(),((Integer)stringSplitingArray[i].Second()).intValue());//String.substtring(begin,end) returns from begin to end-1
                return ArrayofSplitStrings;
        }
}
