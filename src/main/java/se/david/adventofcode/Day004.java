package se.david.adventofcode;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day004 {
    public int solve(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        int answer = 0;

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        int index = 0;
        while(true) {
            String key = String.format("%s%d", input, index++);

            byte[] hash = messageDigest.digest(key.getBytes("UTF-8"));

            if(DatatypeConverter.printHexBinary(hash).startsWith("00000")) {
                answer = index - 1;
                break;
            }
        }

        return answer;
    }
}
