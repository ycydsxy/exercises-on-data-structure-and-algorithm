package sxy.java.basic.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 关于HashMap中remove()具有返回值
 * 
 * @author Kevin
 * 
 */
public class HashmapRemove {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("1", "test");
		System.out.println(map.remove("0"));
		System.out.println(map.remove("1"));

	}
}
