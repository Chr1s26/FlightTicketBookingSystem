package util;

import java.util.Base64;
import java.util.Date;

public class TokenUtil {
	
	public static String generateToken(String username) {
		long timestamp = new Date().getTime();
		String tokenData = username + ":" + timestamp;
		return Base64.getEncoder().encodeToString(tokenData.getBytes());
	}
	
	public static boolean validateToken(String token) {
		return token != null && token.isEmpty();
	}
	
	
}
