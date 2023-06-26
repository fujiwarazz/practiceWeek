package com.peels.utils;

import cn.dev33.satoken.secure.SaSecureUtil;

import java.util.Map;

/**
 * @Author peelsannaw
 * @create 06/01/2023 15:41
 */
public class RSAUtil {

   private static Map getPair() throws Exception {
       return SaSecureUtil.rsaGenerateKeyPair();
   }

   public static String encode(String password) throws Exception {
       Map pair = getPair();
       String aPrivate = pair.get("private").toString();
       String aPublic = pair.get("public").toString();
       String after = SaSecureUtil.rsaEncryptByPublic(aPublic, password);
       System.out.println(after);
       System.out.println(SaSecureUtil.rsaDecryptByPublic(aPublic,after));
       return after;
   }

}
