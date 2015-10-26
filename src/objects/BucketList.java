package objects;

import java.util.ArrayList;

/**
 * this object is a list of buckets that is an array-list plus an integer that show number of prefixes per bucket.
 *
 * @author Soroush
 */
public class BucketList extends ArrayList<Bucket> {
	int prefixes;
	
	/**
     * Get prefixes per bucket in this bucket-list.
     *
	 * @author Soroush
	 * @return
	 */
	public int getPrefixes() {
		return prefixes;
	}
	
	/**
     * Set prefixes per bucket in this bucket-list.
	 * @author Soroush
	 * @param prefixes
	 *
	 */
	public void setPrefixes(int prefixes) {
		this.prefixes = prefixes;
	}
}
