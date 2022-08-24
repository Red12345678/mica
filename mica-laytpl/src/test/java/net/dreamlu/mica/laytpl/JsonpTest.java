package net.dreamlu.mica.laytpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

/**
 * 解析 jsonp
 */
class JsonpTest {

	@Test
	void test() throws ScriptException {
		String jsonp = "/**/callback( {\"client_id\":\"123\",\"openid\":\"123\",\"unionid\":\"123\"} )";

		String jsFun = "function callback(json) { return json };";

		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByMimeType("text/javascript");

		engine.eval(jsFun);

		Map json = (Map) engine.eval(jsonp);
		Assertions.assertEquals("123", json.get("client_id"));
		Assertions.assertEquals("123", json.get("openid"));
		Assertions.assertEquals("123", json.get("unionid"));
	}

}
