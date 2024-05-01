package key;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KeyType {
	private BigInteger n;
	private BigInteger e;//{e,n} Public key
	private BigInteger d;//{d,n} Private Key

	public void CreateKeyRSA(int bits) {
		SecureRandom r = new SecureRandom();
		BigInteger p = new BigInteger(bits / 2, 100, r);
		BigInteger q = new BigInteger(bits / 2, 100, r);
		n = p.multiply(q);
		BigInteger PHI_n = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		boolean found = false;
		do {
			e = new BigInteger(bits / 2, 50, r);
			if (PHI_n.gcd(e).equals(BigInteger.ONE) && e.compareTo(PHI_n) < 0) {
				found = true;
			}
		} while (!found);
		d = e.modInverse(PHI_n);
	}

	public void createKeySHA256(int bits) throws NoSuchAlgorithmException {
		SecureRandom random = new SecureRandom();

		byte[] bytes = new byte[bits / 8];
		random.nextBytes(bytes);

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashedBytes = digest.digest(bytes);

		BigInteger bigInteger = new BigInteger(1, hashedBytes);

		BigInteger p = bigInteger.nextProbablePrime();
		BigInteger q = p.nextProbablePrime();
		n = p.multiply(q);
		BigInteger phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		boolean found = false;
		do {
			e = new BigInteger(bits / 2, 50, random);
			if (phi_n.gcd(e).equals(BigInteger.ONE) && e.compareTo(phi_n) < 0) {
				found = true;
			}
		} while (!found);

		d = e.modInverse(phi_n);
	}

	public synchronized BigInteger encryptRSA(BigInteger message,BigInteger e1,BigInteger n1) {
		return message.modPow(e1, n1);
	}

	public synchronized BigInteger decryptRSA(BigInteger message,BigInteger d1,BigInteger n1) {
		return message.modPow(d1, n1);
	}
	
	public synchronized BigInteger encryptSign(BigInteger message,BigInteger d1,BigInteger n1) {
		return message.modPow(d1, n1);
	}

	public synchronized BigInteger decryptSign(BigInteger message,BigInteger e1,BigInteger n1) {
		return message.modPow(e1, n1);
	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getE() {
		return e;
	}

	public BigInteger getD() {
		return d;
	}
}
