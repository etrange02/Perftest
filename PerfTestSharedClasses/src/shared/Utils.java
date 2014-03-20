package shared;

public class Utils {
	
	public static String displayBinaryArray(byte[] array) {

		String str = "[";

		for(int i = 0; i <  array.length; i++) {
			str += array[i]+",";
		}
		
		str +="]";
		
		return str;
	}
}
