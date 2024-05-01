package main;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import key.KeyType;
import person.Person;

public class Main {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String str = "Hello";
		Person bob = new Person();

		bob.setMessage(bob.stringToBigInteger(str));
		BigInteger encryptMes = bob.getKey().encryptRSA(bob.getMessage(), bob.getKey().getE(), bob.getKey().getN());
		BigInteger dencryptMes = bob.getKey().decryptRSA(encryptMes, bob.getKey().getD(), bob.getKey().getN());
		System.out.println(encryptMes);
		System.out.println(bob.BigIntegerToString(dencryptMes));

		KeyType k = new KeyType();
		k.createKeySHA256(256);
		bob.setKey(k);

		bob.setMessage(bob.stringToBigInteger(str));
		encryptMes = bob.getKey().encryptRSA(bob.getMessage(), bob.getKey().getE(), bob.getKey().getN());
		dencryptMes = bob.getKey().decryptRSA(encryptMes, bob.getKey().getD(), bob.getKey().getN());
		System.out.println(encryptMes);
		System.out.println(bob.BigIntegerToString(dencryptMes));
	}
}
