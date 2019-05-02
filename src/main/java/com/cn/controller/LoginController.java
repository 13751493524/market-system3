package com.cn.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.common.contorller.BaseController;
import com.cn.entity.User;
import com.cn.redis.RedisService;
import com.cn.service.UserService;
import com.cn.util.CookiesUtil;
import com.cn.util.SerializeUtil;

@Controller
@Configuration
public class LoginController extends BaseController{
	@Resource
    private UserService userService;
	@Resource RedisService redisService;
	@SuppressWarnings({ "unused", "unused" })
	@RequestMapping(value="/login")
	public @ResponseBody Map login(@RequestBody User user,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{ 
		System.out.println("========进来了=====进来了============");
		Map map = new HashMap();
		String token = null;
		Cookie cookie = CookiesUtil.get(request, "sysToken10");
		//HttpSession session = request.getSession();
		if(cookie == null || cookie.getValue() == null || "".equals(cookie.getValue())){//说明此电脑之前未登陆
			//token = (String) session.getAttribute("userToken"+user.getUserName());
			token = redisService.get("userToken"+user.getUserName());
			if(token == null || "".equals(token)){//说明没有用户正在登录
				token = getUuid();
				User userLogin = userService.login(user);
				if(userLogin != null){
					//session.setAttribute("userToken"+user.getUserName(), token);
					//session.setAttribute(token,userLogin);
					redisService.put("userToken"+user.getUserName(), token);
					redisService.hput("userInfo", token, new String(SerializeUtil.serialize(userLogin)));
					CookiesUtil.setCookies("sysToken10", token, response);
					map.put("msg", "登录成功！");
				}else{
					map.put("msg", "账户不存在或密码错误！");
				}
			}else{//说明有用户正在登录,则逼退之前的用户
				System.out.println("===========2222==============>");
				User userLogin = userService.login(user);
				if(userLogin != null){
					String newToken = getUuid();
					//session.setAttribute("userToken"+user.getUserName(),newToken);//移除之前的token,建立新的token
					//session.removeAttribute(token);
					//session.setAttribute(newToken, userLogin);
					redisService.put("userToken"+user.getUserName(),newToken);
					redisService.hRemove("userInfo", token);
					System.out.println(new String(SerializeUtil.serialize(userLogin)));
					redisService.hput("userInfo",newToken, SerializeUtil.serialize(userLogin));
					CookiesUtil.setCookies("sysToken10", newToken, response);
					map.put("msg", "登录成功！");
				}else{
					map.put("msg", "账户不存在或密码错误！");
				}
			}
			
		}else{//说明此台机器之前登录过
			token = cookie.getValue();
			System.out.println("token:"+token);
			//String reidsToken = (String) session.getAttribute("userToken"+user.getUserName());
			String reidsToken = redisService.get("userToken"+user.getUserName());
			System.out.println("reidsToken:"+reidsToken);
			if(token != null && token.equals(reidsToken)){
				//System.out.println(redisService.hget("userInfo", token).toString().getBytes());
				//User userLogin = (User) session.getAttribute(token);
				System.out.println(redisService.hget("userInfo", token).toString());
				User userLogin = (User)(SerializeUtil.unserialize(redisService.hget("userInfo", token).toString()));
				System.out.println("userLogin:"+userLogin);
				if(userLogin != null){
					map.put("msg", "登录成功！"); 
				}else{
					map.put("msg", "登录已过期！");
				}
			}else{
				map.put("msg", "登录已过期！");
			}
		}
		return map;
	}
	
	/**
     * 注销登录
     * @param session
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.POST)
    public @ResponseBody Map logout(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestBody User user){
    	String userToken = "userToken"+user.getUserName();
    	if(StringUtils.isNotBlank(redisService.get(userToken))){
        	CookiesUtil.setCookies("sysToken10", null, response);//清空cookies
        	//String token = (String) session.getAttribute(userToken);
        	//session.removeAttribute(token);//删除用户信息
        	//session.removeAttribute(userToken);//删除缓存的userToken
        	String token = redisService.get(userToken);
        	redisService.remove(token);
        	redisService.remove(userToken);
        }
    	Map map = new HashMap();
        map.put("logout", "success");
        return map;
    }
    
    private String getUuid(){
    	String uuid = UUID.randomUUID().toString();//
		String token = "UL"+uuid;
		return token;
    }
}
