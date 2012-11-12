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

public class Encryption {
	private static final String SCHEME = "DES";
	private SecretKeyFactory skf;
	private Cipher enigma; 

	public Encryption() {
		try {
			skf = SecretKeyFactory.getInstance(SCHEME);
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
			encRet = byteTString(cipherBytes);

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

	private byte[] stringTBytes(String s){
		byte[] ret = null;
		try {
			ret = s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return ret;
	}	

	private String byteTString(byte[] a){
		String ret = null;
		try {
			ret = new String(a, "UTF-8");
		}catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

	public static void main(String[] args) {
		Encryption e = new Encryption();
		String plain = "Hej lars";
		String cipher = e.encrypt("OMGSECRET!", plain);
		System.out.println(cipher);
	}
}
