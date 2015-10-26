package algorithm;

public class Binary {

	public static String and(String ip, String mask) {
		String ret = "";
		for (int i = 0; i < ip.length(); i++) {
			if (ip.charAt(i) == '1' && mask.charAt(i) == '1') {
				ret += "1";
			} else {
				ret += "0";
			}
		}
		return ret;
	}

	public static int compare(String bin1, String bin2) {
		int int1 = Integer.parseInt(bin1, 2);
		int int2 = Integer.parseInt(bin2, 2);
		
		if (int1 < int2) return 1;
		else if (int1 > int2) return -1;
		else return 0;
	}

}
