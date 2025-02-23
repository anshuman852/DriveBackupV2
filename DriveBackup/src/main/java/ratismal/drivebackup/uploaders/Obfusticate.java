package ratismal.drivebackup.uploaders;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Obfusticate {
    private static final byte[] keyValue = "kYVcmTxrWHUEk3K2ezM6Uu5a".getBytes();
    
    public static void main(String[] args) {
        try {
            String encrypted = encrypt(args[0]);
            System.out.println(encrypted);
            System.out.println(decrypt(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String valueToEnc) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);

        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        
        byte[] encryptedValue = Base64.getEncoder().encode(encValue);

        return new String(encryptedValue);
    }

    public static String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue.getBytes());

        byte[] decryptedVal = c.doFinal(decodedValue);
        
        return new String(decryptedVal);
    }

    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, "AES");
    }
}
