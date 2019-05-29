package com.cn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cn.entity.User;
import com.cn.redis.RedisService;
import com.cn.util.CookiesUtil;
import com.cn.util.SerializeUtil;
import com.cn.util.UrlUtils;

import net.sf.json.JSONObject;

//@Component
//@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
	//设置不需要拦截的地址或者资源
	private final String[] nocheckUrls = {"/","/index.html","/login","/login/*","/js/*","*/*.ico","*/*.jpg","*/*.png"}; 
	@Resource 
	RedisService redisService;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    req.setCharacterEncoding("utf-8");
    	res.setCharacterEncoding("utf-8");
    	res.setContentType("text/html;charset=UTF-8");
    	int result = checkSession(req,res);
    	
	    if(urlCheck(req)){
	    	filterChain.doFilter(request, response);
	    }else if(result == -1){
	    	Map map = new HashMap();
	    	map.put("msg", "登录超时");
	    	PrintWriter out = res.getWriter();
	    	out.print(JSONObject.fromObject(map).toString());
	    	out.flush();
	    	out.close();
	    }else{
			filterChain.doFilter(request, response);
	    }
	}
	
	/**
	 * 设置不需要拦截的路径
	 * @param req
	 * @return
	 */
	private boolean urlCheck(HttpServletRequest req){
		String uri = req.getRequestURI();
		for(int i=0;i<nocheckUrls.length;i++){
			if(UrlUtils.matching(nocheckUrls[i], uri)){
				return true;
			}
		}
		return false;
	}
	
	private int checkSession(HttpServletRequest request,HttpServletResponse response){
		Cookie cookie = CookiesUtil.get(request, "userLoginToken");
		if(cookie != null){
			String sessionToken = cookie.getValue();
			if(StringUtils.isNotBlank(sessionToken)){
				Object userObj = redisService.hget("userInfo", sessionToken);
				if(userObj != null && StringUtils.isNotBlank(userObj.toString())){
					User sessionUser = (User)(SerializeUtil.unserialize(redisService.hget("userInfo", sessionToken).toString()));
					if(sessionUser != null) return 0;
				}
			}
		}
		CookiesUtil.removeCookies("userLoginToken", request, response);//删除cookies
		return -1;
	}

}
