package rsa;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sign {
	public String sign(BigInteger[] private_key, BigInteger[] public_key, String message, BigInteger encoded_message) throws NoSuchAlgorithmException {
		BigInteger n = public_key[0];
		BigInteger d = private_key[2];
		
		BigInteger ciphertext = encoded_message.modPow(d, n);

		System.out.println("texto assinado " + ciphertext);
		
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-256");
		String text = "Text to hash, cryptographically.";
	    md.update(text.getBytes(StandardCharsets.UTF_8));
	    byte[] digest = md.digest();

		
	    String hex = String.format("%064x", new BigInteger(1, digest));
	    System.out.println("Hash da Mensagem: " + hex);
		
		return hex;    	
	}
}
