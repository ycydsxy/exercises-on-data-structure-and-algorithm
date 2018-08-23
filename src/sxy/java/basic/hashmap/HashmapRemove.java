package sxy.java.basic.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 关于HashMap中remove()的返回值
 * 
 * @author Kevin
 * 
 */
public class HashmapRemove {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("1", "test");
		System.out.println(map);
		System.out.println(map.remove("1"));
		System.out.println(map.remove("2"));
		System.out.println(map);
	}
}
