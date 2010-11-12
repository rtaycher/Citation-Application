package citation.extra;
/***
 *Simple Tuple Class that swallows two objects.
 */
public class STuple {
private Object fst;
private Object snd;

/***
 * constructs a tuple with a fst and snd bject
 */
    public STuple(Object first,Object second){
	    fst=first;
     	snd=second;
    }
    
    public Object First(){
	return fst;
    }
    public Object Second(){
	return snd;
    }
}
