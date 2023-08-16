/**
 * 
 */
package com.eprobj.utill;


import org.apache.shiro.crypto.hash.Md5Hash;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;


/**  
* @ClassName: ShiroUtils
* @Description: shiro封装工具类 
* @author
*/
public class ShiroUtils {

    /**
    * @Description: 加盐
    * @param password
    * @return
    * @throws
     */
    public static String getStrByMD5(String password){
    	return new Md5Hash(password,"3.14159").toString();
    }

    private ShiroUtils() {

	}

    /**
     * 密码加密
	 * 生成密码填充库
     */
	public static String getMD5Str(String password) throws Exception{
		StringBuilder buffer = new StringBuilder("3.14159");
		buffer.append(password);
		String pString = buffer.toString();
		MessageDigest md = MessageDigest.getInstance("MD5");
		char[] charArray = pString.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for(int i=0;i<charArray.length;i++){
			byteArray[i] = (byte) charArray[i];
		}
		byte[] mdBytes = md.digest(byteArray);
		StringBuilder hexValue = new StringBuilder();
		for(int i=0;i<mdBytes.length;i++){
			int val = ((int)mdBytes[i])&0xff;
			if(val<16){
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 *判断登录密码是否正确
	 */
	public static boolean 	isEqualsPassword(String loginpassword,String dbpassword){
		if(loginpassword!=null&&dbpassword!=null){
			try {
				if(dbpassword.equals ( getMD5Str ( loginpassword ) )){
					return true;
                }else{
					return false;
				}
			} catch ( Exception e ) {
				e.printStackTrace ( );
			}
		}
			return false;
	}

	/**
	 * @Description: 是否是Ajax请求
	* @param request
	* @return
	* @throws  
	 */
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}
	

    
    
    
}
