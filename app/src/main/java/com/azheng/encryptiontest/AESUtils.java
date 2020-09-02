package com.azheng.encryptiontest;

import com.azheng.encryptiontest.misc.BASE64Decoder;
import com.azheng.encryptiontest.misc.BASE64Encoder;


import java.security.MessageDigest;

import javax.crypto.Cipher;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    private static final int KEY_SIZE = 32;
    public static final String ALGORIGTHM_NAME = "AES";
    public static final String charsetName = "utf-8";
    private static final String mKey = "fb256a9a1fad11ea";
    private static final String mEncryptMode = "ECB";

    /**
     * AES加密
     *
     * @param plaintext 明文
     *  mKey 密钥
     * mEncryptMode AES加密模式，CBC或ECB
     * @return 该字符串的AES密文值
     */
    public static String aESEncrypt(Object plaintext) {
        String PlainText=null;
        try {
            PlainText=plaintext.toString();

            byte[] raw = mKey.getBytes(charsetName);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/"+mEncryptMode+"/PKCS5Padding");
            if("ECB".equals(mEncryptMode)) {
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            }else {
                //使用CBC模式，需要一个向量iv，可增加加密算法的强度
                IvParameterSpec iv = new IvParameterSpec(mKey.getBytes(charsetName));
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            }
            byte[] encrypted = cipher.doFinal(PlainText.getBytes(charsetName));
            String encryptedStr=new String(new BASE64Encoder().encode(encrypted));
            return encryptedStr;
            //return new String(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * AES解密
     *
     * @param cipertext 密文
     * @param Key 密钥
     *  AES加密模式，CBC或ECB
     * @return 该密文的明文
     */
    public static String aESDecrypt(Object cipertext, String Key) {
        String ciphertext =null;
        try {
            ciphertext=cipertext.toString();

            byte[] raw = Key.getBytes(charsetName);
            SecretKeySpec skeySpec = new SecretKeySpec(raw,ALGORIGTHM_NAME);
            Cipher cipher=Cipher.getInstance("AES/"+mEncryptMode+"/PKCS5Padding");
            if("ECB".equals(mEncryptMode)) {
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            } else {
                //使用CBC模式，需要一个向量iv，可增加加密算法的强度
                IvParameterSpec iv = new IvParameterSpec(raw);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            }
            //先用base64解密
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(ciphertext);
            //byte[] encrypted1 = CipherText.getBytes();
            try {
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original,charsetName);
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * AES解密
     *
     * @param cipertext 密文
     *  密钥
     *  AES加密模式，CBC或ECB
     * @return 该密文的明文
     */
    public static String aESDecrypt(Object cipertext) {
        String ciphertext =null;
        try {
            ciphertext=cipertext.toString();
            byte[] raw = mKey.getBytes(charsetName);
            SecretKeySpec skeySpec = new SecretKeySpec(raw,ALGORIGTHM_NAME);
            Cipher cipher=Cipher.getInstance("AES/"+mEncryptMode+"/PKCS5Padding");
            if("ECB".equals(mEncryptMode)) {
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            } else {
                //使用CBC模式，需要一个向量iv，可增加加密算法的强度
                IvParameterSpec iv = new IvParameterSpec(raw);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            }
            //先用base64解密
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(ciphertext);
            //byte[] encrypted1 = CipherText.getBytes();
            try {
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original,charsetName);
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
