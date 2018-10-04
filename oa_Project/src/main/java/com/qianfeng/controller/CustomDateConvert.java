package com.qianfeng.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConvert implements Converter<String, Date>{

	@Override
	public Date convert(String info) {
		// TODO Auto-generated method stub
		if(info == null || info.isEmpty()){
			return null;
		}
		// 定义数组，保存支持的日期字符串的格式
		SimpleDateFormat[] sdfs = new SimpleDateFormat[]{
				new SimpleDateFormat("yyyy-MM-dd"),
				new SimpleDateFormat("yyyy/MM/dd"),
				new SimpleDateFormat("yyyyMMdd")
		};
		for (SimpleDateFormat sdf : sdfs) {
			
			// 受检异常、非受检异常
			try {
				return sdf.parse(info);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				continue;
			}
		}
		
		return null;
	}

}
