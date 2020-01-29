package by.library.scanner;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    private static Hashing hashing;

    private Hashing(){}

    public static Hashing getInstance() {
        if (hashing == null) {
        	hashing = new Hashing();
        }
        return hashing;
    }


    public String getHashedPassword(String password) {
        
    	try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes("UTF-8"));
            byte[] hash = digest.digest();
            return new String(hash);
        }catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
