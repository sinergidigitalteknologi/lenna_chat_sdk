package ai.lenna.lennachatmodul.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesCipher {
    public AesCipher() {
    }

    public static String encrypt(String secureKey, String textToEncrypt) throws Exception
    {
        int ssnLength = 32 - secureKey.length();
        for (int i = 0; i < ssnLength; i++){
            secureKey = secureKey.concat("0");
        }
        Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(secureKey.getBytes("UTF-8"),"AES");
        IvParameterSpec iv = new IvParameterSpec("0000000000000000".getBytes("UTF-8"),0,ciper.getBlockSize());

        //Encrypt
        ciper.init(Cipher.ENCRYPT_MODE, key,iv);
        String encryptedCiperBytes = Base64.encodeToString((ciper.doFinal(textToEncrypt.getBytes())), Base64.DEFAULT);
        System.out.println("Ciper : " + encryptedCiperBytes);
        String c = encryptedCiperBytes.replace(System.getProperty("line.separator"), "");
        return c;
    }

    public static String decrypt(String secureKey, String encryptText) throws Exception
    {
        int ssnLength = 32 - secureKey.length();
        for (int i = 0; i < ssnLength; i++){
            secureKey = secureKey.concat("0");
        }

        Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(secureKey.getBytes("UTF-8"),"AES");
        IvParameterSpec iv = new IvParameterSpec("0000000000000000".getBytes("UTF-8"),0,ciper.getBlockSize());

        //Decrypt
        ciper.init(Cipher.DECRYPT_MODE, key,iv);
        byte[] text = ciper.doFinal(Base64.decode(encryptText, Base64.DEFAULT));
        System.out.println("Decrypt text : " + new String(text));

        return new String(text);
    }
}
