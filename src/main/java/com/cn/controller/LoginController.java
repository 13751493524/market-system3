package com.cn.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
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
	
	@RequestMapping(value="/login")
	public @ResponseBody Map login(@RequestBody User user,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{ 
		Map map = null;
		try {
			map = new HashMap();
			User userLogin = userService.login(user);
			if(userLogin != null){
				String sessionToken = redisService.get("userToken"+user.getUserName());
				if(StringUtils.isNotBlank(sessionToken)){
					User sessionUser = null;
					Object userObj = redisService.hget("userInfo", sessionToken);
					if(userObj != null && StringUtils.isNotBlank(userObj.toString())){
						sessionUser = (User)(SerializeUtil.unserialize(userObj.toString()));
					}
					redisService.remove(sessionToken);
					if(sessionUser != null){
						redisService.hRemove("userInfo", sessionToken);
					}
				}
				String token = getUuid();
				redisService.put("userToken"+user.getUserName(), token);
				redisService.hput("userInfo", token, new String(SerializeUtil.serialize(userLogin)));
				CookiesUtil.setCookies("userLoginToken", token, response);
				map.put("code", "0");
				map.put("msg", "登录成功！");
			}else{
				map.put("code", "-1");
				map.put("msg", "账户不存在或密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
     * ע����¼
     * @param session
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.POST)
    public @ResponseBody Map logout(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestBody User user){
    	CookiesUtil.removeCookies("userLoginToken", request, response);//ɾ��cookies
    	String userToken = "userToken"+user.getUserName();//���reids�ϵļ�¼
    	if(StringUtils.isNotBlank(redisService.get(userToken))){
        	String token = redisService.get(userToken);
        	redisService.hRemove("userInfo",token);
        	//redisService.remove(userToken);
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
