package extra;
/***
 *Simple Tuple Class to allow returning two answers simply.
 */
public class STuple {
private Object fst;
private Object snd;

/***
 * constructs a tuple with a fst and snd object
 */
    public STuple(Object first,Object second)
    {
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
