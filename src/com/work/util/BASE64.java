package com.work.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 数据重新编码
 * @author Administrator
 * @creation 2017
 */
public class BASE64 {
    /**  
     * BASE64解密 
     *   
     * @param key  
     * @return  
     * @throws IOException 
     * @throws Exception  
     */  
    public static byte[] decryptBASE64(String key) throws IOException  {   
     
		
				return (new BASE64Decoder()).decodeBuffer(key);
			
		 
    }   
      
    /**  
     * BASE64加密  
     *   
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static String encryptBASE64(byte[] key) throws Exception {   
        return (new BASE64Encoder()).encodeBuffer(key);   
    }       
        
    }
