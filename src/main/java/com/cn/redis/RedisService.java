package com.cn.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	@Autowired
	private StringRedisTemplate template;
	
	public void setTemplate(StringRedisTemplate template) {
		this.template = template;
	}
	
	public void put(String key,String value){
		template.opsForValue().set(key, value);
	}
	
	public String get(String key){
		return template.opsForValue().get(key);
	}
	
	public void remove(String key){
		template.delete(key);
	}
	
	public void hput(String mapName,String key,Object value){
		template.opsForHash().put(mapName, key, value);
	}
	
	public Object hget(String mapName,String key){
		return template.opsForHash().get(mapName, key);
	}
	
	public void hRemove(String mapName,String key){
		template.opsForHash().delete(mapName, key);
		
	}
}
