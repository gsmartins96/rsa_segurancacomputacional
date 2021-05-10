package rsa;

import java.math.BigInteger;

public class EncodeDecode {
	
	
	public BigInteger encode(String message) {
		byte[] b = new byte[message.length()];
	      for (int i = 0; i < b.length; i++)
	         b[i] = (byte)message.charAt(i);
	      return new BigInteger(1,b);
	}
	
	public String decode(BigInteger message) {
		byte[] b = message.toByteArray();
	      StringBuffer s = new StringBuffer();
	      for (int i = 0; i < b.length; i++)
	         s.append((char)b[i]);
	      return s.toString();
	}
}
