package Server;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpImpl {
    @RequestMapping(value = "testInterfaces.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public void testInterfaces(HttpServletRequest request) throws Exception {
        String param = "{\"url\":\"中文\"}";
        param = URLEncoder.encode(param, StandardCharsets.UTF_8);
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL("http://localhost/test.htm");
            URLConnection connection = realUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            out.println(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            JSONObject jsObject = JSONObject.fromObject(result.toString());
            System.out.println(jsObject.get("firstName"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "test.htm", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject test(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("firstName", "Jack");
        jsonObject.putAll(map);
        return jsonObject;
    }
}

