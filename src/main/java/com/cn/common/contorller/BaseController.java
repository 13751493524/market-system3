package com.cn.common.contorller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;

import com.cn.entity.User;
import com.cn.redis.RedisService;
import com.cn.util.CookiesUtil;
import com.cn.util.SerializeUtil;

@ComponentScan({"com.cn.service","com.cn.redis"})
public class BaseController{
	@Resource
	protected RedisService redisService;
	protected User getSessionUser(HttpServletRequest request){
		Cookie cookie = CookiesUtil.get(request, "userLoginToken");
		String sessionToken = cookie.getValue();
		Object userObj = redisService.hget("userInfo", sessionToken);
		if(userObj != null && StringUtils.isNotBlank(userObj.toString())){
			User sessionUser = (User)(SerializeUtil.unserialize(userObj.toString()));
			return sessionUser;
		}
		return 	null;
	}
}
