package com.cn.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
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
	@RequestMapping(value="/login")
	public @ResponseBody Map login(@RequestBody User user,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{ 
		Map map = new HashMap();
		String token = null;
		Cookie cookie = CookiesUtil.get(request, "sysToken10");
		if(cookie == null || cookie.getValue() == null || "".equals(cookie.getValue())){//˵���˵���֮ǰδ��½
			token = redisService.get("userToken"+user.getUserName());
			if(token == null || "".equals(token)){//˵��û���û����ڵ�¼
				token = getUuid();
				User userLogin = userService.login(user);
				if(userLogin != null){
					redisService.put("userToken"+user.getUserName(), token);
					redisService.hput("userInfo", token, new String(SerializeUtil.serialize(userLogin)));
					CookiesUtil.setCookies("sysToken10", token, response);
					map.put("msg", "��¼�ɹ���");
				}else{
					map.put("msg", "�˻������ڻ��������");
				}
			}else{//˵�����û����ڵ�¼,�����֮ǰ���û�
				User userLogin = userService.login(user);
				if(userLogin != null){
					String newToken = getUuid();
					redisService.put("userToken"+user.getUserName(),newToken);
					redisService.hRemove("userInfo", token);
					System.out.println(new String(SerializeUtil.serialize(userLogin)));
					redisService.hput("userInfo",newToken, SerializeUtil.serialize(userLogin));
					CookiesUtil.setCookies("sysToken10", newToken, response);
					map.put("msg", "��¼�ɹ���");
				}else{
					map.put("msg", "�˻������ڻ��������");
				}
			}
			
		}else{//˵����̨����֮ǰ��¼��
			token = cookie.getValue();
			System.out.println("token:"+token);
			String reidsToken = redisService.get("userToken"+user.getUserName());
			System.out.println("reidsToken:"+reidsToken);
			if(token != null && token.equals(reidsToken)){
				System.out.println(redisService.hget("userInfo", token).toString());
				User userLogin = (User)(SerializeUtil.unserialize(redisService.hget("userInfo", token).toString()));
				System.out.println("userLogin:"+userLogin);
				if(userLogin != null){
					map.put("msg", "��¼�ɹ���"); 
				}else{
					map.put("msg", "��¼�ѹ��ڣ�");
				}
			}else{
				map.put("msg", "��¼�ѹ��ڣ�");
			}
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
    	String userToken = "userToken"+user.getUserName();
    	if(StringUtils.isNotBlank(redisService.get(userToken))){
        	CookiesUtil.setCookies("sysToken10", null, response);//���cookies
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
