package person;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import key.KeyType;

public class Person {
	private BigInteger message;
	private String strMessage;
	private KeyType key;

	public Person() throws NoSuchAlgorithmException {
		key.KeyType k1 = new key.KeyType();
		k1.CreateKeyRSA(256);
		this.key = k1;
	}

	public BigInteger stringToBigInteger(String str) {
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			String binary = Integer.toBinaryString(c);
			while (binary.length() < 8) {
				binary = "0" + binary;
			}
			sb.append(binary);
		}
		return new BigInteger(sb.toString(), 2);
	}

	public String BigIntegerToString(BigInteger bigInt) {
		String binaryString = bigInt.toString(2);
		while (binaryString.length() % 8 != 0) {
			binaryString = "0" + binaryString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < binaryString.length(); i += 8) {
			String binaryChar = binaryString.substring(i, i + 8);
			sb.append((char) Integer.parseInt(binaryChar, 2));
		}
		return sb.toString();
	}

	public BigInteger getMessage() {
		return message;
	}

	public void setMessage(BigInteger message) {
		this.message = message;
	}

	public String getStrMessage() {
		return strMessage;
	}

	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}

	public key.KeyType getKey() {
		return key;
	}

	public void setKey(key.KeyType key) {
		this.key = key;
	}
}
