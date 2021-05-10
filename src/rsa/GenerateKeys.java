package rsa;

import java.math.BigInteger;
import java.util.Random;

public class GenerateKeys {
	private BigInteger p = BigInteger.probablePrime(1024, new Random());
	private BigInteger q = BigInteger.probablePrime(1024, new Random());
	private BigInteger e = BigInteger.probablePrime(1024, new Random());
	private BigInteger n = p.multiply(q);
	private BigInteger totiente = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
	
	private BigInteger generatePublicKey() {
		while (totiente.gcd(e).compareTo(BigInteger.valueOf(1)) != 0) {
		    e = e.add(BigInteger.valueOf(1));
		}
		
		return e;
	}
	
	// Calculates the multiplicative inverse to obtain the private key
	private BigInteger generatePrivateKey() {
		BigInteger d = e.modInverse(totiente);
		
		return d;
	}
	
	public BigInteger[] getPrivateKey() {
		BigInteger d = generatePrivateKey();
		BigInteger[] private_key = new BigInteger[3];
		
		private_key[0] = p;
		private_key[1] = q;
		private_key[2] = d;
		
		
		return private_key;
	}
	
	
	public BigInteger[] getPublicKey() {
		BigInteger e = generatePublicKey();
		BigInteger[] public_key = new BigInteger[2];
		
		public_key[0] = n;
		public_key[1] = e;
		
		return public_key;
	}
}
