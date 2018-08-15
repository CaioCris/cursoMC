package br.com.javaspring.cursomc.resource.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static String decodeParam(String urlName) {
		try {
			return URLDecoder.decode(urlName, "UTF-8");
		}catch (UnsupportedEncodingException error){
			return "";
		}
	}

	public static List<Integer> decodeIntList(String urlList) {
		return Arrays.asList(urlList.split(","))
				.stream()
				.map(v -> Integer.parseInt(v))
				.collect(Collectors.toList());
	}
}
