package util;

public class ParamUtil {
	
	public static String convertArrayToString(String[] arr) {
		String str = "";
		for (String item : arr) {
			str += item + " ";
		}
		
		return str;
	}
	

}
