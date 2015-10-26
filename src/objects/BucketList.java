package objects;

import java.util.ArrayList;

@SuppressWarnings("serial")
/**
 * @author Soroush
 * this object is a list of buckets that is an array-list plus an integer that show number of prefixes per bucket.
 */
public class BucketList extends ArrayList<Bucket> {
	int prefixes;
	
	/**
	 * @author Soroush
	 * @return prefixes per bucket in this bucket-list
	 */
	public int getPrefixes() {
		return prefixes;
	}
	
	/**
	 * @author Soroush
	 * @param prefixes
	 * sets prefixes per bucket in this bucket-list
	 */
	public void setPrefixes(int prefixes) {
		this.prefixes = prefixes;
	}
}
