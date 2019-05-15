package com.cn.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class CookiesUtil {
	public static Cookie get(HttpServletRequest request,String name){
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			return cookieMap.get(name);
		}else {
			return null;
		}
	}
		
	/**
	 * 将cookie封装成Map
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
	    Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie: cookies) {
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
	public static void setCookies(String key,String value,HttpServletResponse response){
		Cookie keyCookie = new Cookie(key, value);
		response.addCookie(keyCookie);
	}
	
	public static void removeCookies(String key,HttpServletRequest request,HttpServletResponse response){
		Cookie cookie = CookiesUtil.get(request, key);
    	if(cookie != null && StringUtils.isNotBlank(cookie.getValue())){
    		CookiesUtil.setCookies(key, null, response);//���cookies
    	}
	}
}
