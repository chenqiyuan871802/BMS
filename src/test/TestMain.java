package test;

import java.util.Map;

import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.impl.HashDto;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class TestMain {
 
	public static void main(String[] args) throws ApiException {
		String url="http://gw.api.taobao.com/router/rest";
	    String appkey="23812181";
		String secret="e0c94368229a2383f0723ac381e1d895"	;
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("美妍社移动应用发布");
		req.setSmsParamString("{\"name\":\"陈骑元\"}");
		req.setRecNum("13802907704");
		req.setSmsTemplateCode("SMS_66795136");
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		String str=rsp.getBody();
		System.out.println(str);
		Dto dataDto=IMSJson.fromJson(str, HashDto.class	);
		Map map=(Map)dataDto.get("error_response");
        String errorCodeAndMsg= map.get("sub_code")+"["+map.get("sub_msg")+"]";
		System.out.println(errorCodeAndMsg);
		
	}
}
