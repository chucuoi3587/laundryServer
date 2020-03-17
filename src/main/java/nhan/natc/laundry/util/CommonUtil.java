package nhan.natc.laundry.util;

public class CommonUtil {

	public static boolean stringIsNullOrEmpty(String data) {
		if (data != null && !data.equals(""))
			return false;
		return true;
	}
}
