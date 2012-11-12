package lib;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.KeySpec;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;

public class Encryption {
	private static final String SCHEME = "DES";
	private SecretKeyFactory skf;
	private Cipher enigma; 

	public Encryption() {
		try {
			skf = SecretKeyFactory.getInstance("DES");
			enigma = Cipher.getInstance(SCHEME);
		}catch(NoSuchAlgorithmException | NoSuchPaddingException e) {
			
			throw new RuntimeException(e);
		}
	}

	public String encrypt(String key, String plain) {
		String encRet = null;
		try {
			enigma.init(Cipher.ENCRYPT_MODE, getKey(key));
			byte[] cipherBytes = enigma.doFinal(stringTBytes(plain));
			encRet = new String(Base64.encodeBase64(cipherBytes));
		}catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
		return encRet;
	}

	public String decrypt(String key, String plain) {
		String encRet = null;
		try {
			enigma.init(Cipher.DECRYPT_MODE, getKey(key));
			byte[] cipherBytes = Base64.decodeBase64(plain);
			byte[] plainBytes = enigma.doFinal(cipherBytes);
			encRet = new String(plainBytes);
		}catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
		return encRet;
	}

	private SecretKey getKey(String k) {
		SecretKey sk = null;
		try {
			KeySpec keyspec = new DESKeySpec(stringTBytes(k));
			sk = skf.generateSecret(keyspec);
		} catch(InvalidKeyException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
		return sk;
	}

	public static void main(String[] args) {
		Encryption e = new Encryption();
		String plain = "Hej lars";
		String cipher = e.encrypt("OMGSECRET!", plain);
		String decPlain = e.decrypt("OMGSECRET!", cipher);
		System.out.println("plain:\t\t\t" + plain);
		System.out.println("cipher:\t\t\t" + cipher);
		System.out.println("deciphered:\t" + decPlain);
	}
}
