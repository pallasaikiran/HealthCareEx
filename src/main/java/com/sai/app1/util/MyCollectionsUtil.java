package com.sai.app1.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Java8 (static & default methods allowed)
public interface MyCollectionsUtil {
	
	public static Map<Long,String> convertToMap(List<Object[]> list)
	{
		//Java 8 Stream API
		Map<Long,String> map=
				list.
				stream().
				collect(
						Collectors.toMap(
										ob->Long.valueOf(ob[0].toString()),
										ob->ob[1].toString()));
		return map;
		
	}
}
