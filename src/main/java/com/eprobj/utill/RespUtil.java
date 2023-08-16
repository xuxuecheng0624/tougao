package com.eprobj.utill;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class RespUtil {

	private final static FastJsonConfig config = new FastJsonConfig();
	static {
		config.setDateFormat(JSON.DEFFAULT_DATE_FORMAT);
		config.setCharset(Charset.forName("UTF-8"));
		config.setSerializerFeatures(
				SerializerFeature.QuoteFieldNames,  // 输出key时是否使用双引号,默认为true
				SerializerFeature.WriteMapNullValue,  // 是否输出值为null的字段,默认为false
				SerializerFeature.WriteNullNumberAsZero,  // 数值类型字段null值输出为0
				SerializerFeature.WriteNullListAsEmpty,  // List字段null值输出为[]
				SerializerFeature.WriteNullStringAsEmpty,  // 字符类型字段null值输出为""
				SerializerFeature.WriteNullBooleanAsFalse,  //  Boolean字段null值输出为false
				SerializerFeature.WriteDateUseDateFormat,  //  日期类型字段格式化输出
				SerializerFeature.DisableCircularReferenceDetect  // 禁止循环引用
				);
		config.setSerializeFilters(new ValueFilter() {
			@Override
			public Object process(Object obj, String name, Object value) {
				if(value==null) {
	                return "";
	            }
	            return value;
			}
		});
	}

	public static String getResp(int code, String msg, Object data) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put("code", code);
		respMap.put("msg", msg);
		respMap.put("data", data);
		return RespUtil.getJSONString(respMap);
	}
	public static String getResp(int code, String msg, Object data, long count) {
        Map<String,Object> respMap = new HashMap<String, Object>();
        respMap.put("code", code);
        respMap.put("msg", msg);
        respMap.put("data", data);
        respMap.put("count", count);
        return RespUtil.getJSONString(respMap);
    }
	/**
	 * 将指定对象转换为JSONString.
	 * @param data
	 * @return
	 */
	public static String getJSONString(Object data) {
		return JSON.toJSONString(data, config.getSerializeConfig(), config.getSerializeFilters(), config.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, config.getSerializerFeatures());
	}

	public static final int RESP_CODE_SUCCESS = 0;  // 成功
	public static final int RESP_CODE_FAIL = -1;  // 失败
	public static final int RESP_CODE_ERROR = -2;  // 错误
	public static final int RESP_CODE_EXCEPTION = -3;  // 异常

//	public static void main(String[] args) {
//		Map<String,Object> data = new HashMap<String,Object>();
//		data.put("null", null);
//		data.put("curDate", new Date());
//		data.put("curTime", new Timestamp(new Date().getTime()));
//		System.out.println(RespUtil.getResp(RESP_CODE_SUCCESS, "调用成功", data));
//	}
	
}
