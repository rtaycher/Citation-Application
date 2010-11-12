package citation.extra;
/**
 * I couldn't decide whether to use an array or Enumaration
 * Iterator to keep track of the next tag so I did both.
 */
public interface EnumLike {

	public abstract int enumval(String key);
    public abstract String nextReadable();
	public abstract String nextTag();

}