package rsa;

import java.math.BigInteger;

public class EncryptDecrypt {
	
	
	public BigInteger encrypt(BigInteger[] public_key, BigInteger plaintext) {
		BigInteger n = public_key[0];
		BigInteger e = public_key[1];
		
		BigInteger ciphertext = plaintext.modPow(e, n);
		
		return ciphertext;
	}
	
	public BigInteger decrypt(BigInteger[] private_key, BigInteger[] public_key, BigInteger ciphertext) {
		BigInteger n = public_key[0];
		BigInteger d = private_key[2];
		
		BigInteger plaintext = ciphertext.modPow(d, n);
		
		return plaintext;
	}
}
