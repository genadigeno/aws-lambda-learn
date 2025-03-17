package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@LambdaHandler(
    lambdaName = "hello_world2",
	roleName = "hello_world2-role",
	isPublishVersion = true,
	aliasName = "${lambdas_alias_name}",
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
public class HelloWorld2 implements RequestHandler<Object, Map<String, Object>> {

	public Map<String, Object> handleRequest(Object request, Context context) {
		System.out.println("Hello from lambda");
		String message;
		int statusCode;

		Map requestMap = (Map) request;
		Object path = requestMap.get("path");

		if (path != null && "/hello".equals(path.toString())) {
			statusCode = 200;
			message = "Hello from Lambda";
		} else {
			statusCode = 400;
			message = "Bad request syntax or unsupported method. Request path: {path}. HTTP method: {method}";
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("statusCode", statusCode);
		resultMap.put("message", message);
		return resultMap;
	}

}
