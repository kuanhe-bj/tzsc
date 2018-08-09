package io.renren.vas.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 公安部一所视图库对接http请求专用工具
 *
 * @author zhangyulong
 * @date 2018/1/29
 */
@Slf4j
public class HttpUtil {

    public static final int HTTP_200 = 200;
    public static final int HTTP_202 = 202;
    public static final int HTTP_204 = 204;
    public static final int HTTP_401 = 401;
    public static final int HTTP_403 = 403;

    private static final String SERVLET_POST = "POST";
    private static final String SERVLET_GET = "GET";
    private static final String SERVLET_DELETE = "DELETE";
    private static final String SERVLET_PUT = "PUT";

    public Map request(String requestUrl, String requestMethod, String heads, String msg) {
        log.info("请求参数：[requestUrl:{},requestMethod:{},heads:{},msg:{}]", requestUrl, requestMethod, heads, msg);
        String destURLStr = requestUrl;
        URL destURL = null;
        HttpURLConnection httpUrlCon = null;
        StringBuffer result = new StringBuffer();
        Map response = null;
        try {
            destURL = new URL(destURLStr);
            httpUrlCon = (HttpURLConnection) destURL.openConnection();
            //先设为3秒超时，避免调试程序时等待过长
            httpUrlCon.setConnectTimeout(3000);
            httpUrlCon.setDoOutput(true);
            httpUrlCon.setDoInput(true);
            httpUrlCon.setRequestMethod(requestMethod);
            httpUrlCon.setUseCaches(false);
            httpUrlCon.setInstanceFollowRedirects(true);

            httpUrlCon.setRequestProperty("Accept-Encoding", "gzip,deflate");
            httpUrlCon.setRequestProperty("Content-Type", "application/*+JSON;charset=utf-8");
            httpUrlCon.setRequestProperty("Accept", "application/viid+json,application/json,application/*+json,text/plain,*/*");
            httpUrlCon.setRequestProperty("Accept-Charset", "big5-hkscs, cesu-8, euc-jp, euc-kr, gb18030, gb2312, gbk, ibm-thai, ibm00858, ibm01140, ibm01141, ibm01142, ibm01143, ibm01144, ibm01145, ibm01146, ibm01147, ibm01148, ibm01149, ibm037, ibm1026, ibm1047, ibm273, ibm277, ibm278, ibm280, ibm284, ibm285, ibm290, ibm297, ibm420, ibm424, ibm437, ibm500, ibm775, ibm850, ibm852, ibm855, ibm857, ibm860, ibm861, ibm862, ibm863, ibm864, ibm865, ibm866, ibm868, ibm869, ibm870, ibm871, ibm918, iso-2022-cn, iso-2022-jp, iso-2022-jp-2, iso-2022-kr, iso-8859-1, iso-8859-13, iso-8859-15, iso-8859-2, iso-8859-3, iso-8859-4, iso-8859-5, iso-8859-6, iso-8859-7, iso-8859-8, iso-8859-9, jis_x0201, jis_x0212-1990, koi8-r, koi8-u, shift_jis, tis-620, us-ascii, utf-16, utf-16be, utf-16le, utf-32, utf-32be, utf-32le, utf-8, windows-1250, windows-1251, windows-1252, windows-1253, windows-1254, windows-1255, windows-1256, windows-1257, windows-1258, windows-31j, x-big5-hkscs-2001, x-big5-solaris, x-compound_text, x-euc-jp-linux, x-euc-tw, x-eucjp-open, x-ibm1006, x-ibm1025, x-ibm1046, x-ibm1097, x-ibm1098, x-ibm1112, x-ibm1122, x-ibm1123, x-ibm1124, x-ibm1364, x-ibm1381, x-ibm1383, x-ibm300, x-ibm33722, x-ibm737, x-ibm833, x-ibm834, x-ibm856, x-ibm874, x-ibm875, x-ibm921, x-ibm922, x-ibm930, x-ibm933, x-ibm935, x-ibm937, x-ibm939, x-ibm942, x-ibm942c, x-ibm943, x-ibm943c, x-ibm948, x-ibm949, x-ibm949c, x-ibm950, x-ibm964, x-ibm970, x-iscii91, x-iso-2022-cn-cns, x-iso-2022-cn-gb, x-iso-8859-11, x-jis0208, x-jisautodetect, x-johab, x-macarabic, x-maccentraleurope, x-maccroatian, x-maccyrillic, x-macdingbat, x-macgreek, x-machebrew, x-maciceland, x-macroman, x-macromania, x-macsymbol, x-macthai, x-macturkish, x-macukraine, x-ms932_0213, x-ms950-hkscs, x-ms950-hkscs-xp, x-mswin-936, x-pck, x-sjis_0213, x-utf-16le-bom, x-utf-32be-bom, x-utf-32le-bom, x-windows-50220, x-windows-50221, x-windows-874, x-windows-949, x-windows-950, x-windows-iso2022jp");
            if (heads != null) {
                httpUrlCon.setRequestProperty("Authorization", heads);
            }

            httpUrlCon.connect();
            DataOutputStream out = new DataOutputStream(httpUrlCon.getOutputStream());
            out.write(msg.getBytes("utf-8"));
            out.flush();
            response = this.getResponseHeaders(httpUrlCon);
            // 如果返回的http响应代码是401.表示没有权限，需要返回会响应消息头。进行 “摘要认证”
            if (HTTP_401 == httpUrlCon.getResponseCode()) {
                return response;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlCon.getInputStream(), "utf8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            response.put("result", result);
            log.info("响应结果：[result:{}]", result);
        } catch (Exception e) {
            log.error("{}", e);
            return null;
        }
        return response;
    }

    /**
     * HTTP Post请求
     *
     * @param requestUrl
     * @param heads
     * @param msg
     * @return
     */
    public Map doPost(String requestUrl, String heads, String msg) {
        return this.request(requestUrl, "POST", heads, msg);
    }


    public Map doGet(String requestUrl, String param) {
        try {
            requestUrl = requestUrl + "?" + URLEncoder.encode(param, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("请求数据URLEncoder编码错误：[param:{}]", param);
            return null;
        }
        return this.request(requestUrl, "GET", null, null);

    }

    /**
     * 把Map参数拼装成String类型的请求参数
     *
     * @param paramMap
     * @return
     */
    private static String prepareParam(Map<String, Object> paramMap) {
        StringBuffer sb = new StringBuffer();
        if (paramMap.isEmpty()) {
            return "";
        } else {
            for (String key : paramMap.keySet()) {
                String value = (String) paramMap.get(key);
                if (sb.length() < 1) {
                    sb.append(key).append("=").append(value);
                } else {
                    sb.append("&").append(key).append("=").append(value);
                }
            }
            return sb.toString();
        }
    }


    /**
     * 获取HTTP响应头信息
     *
     * @param httpUrlCon
     * @return
     * @throws IOException
     */
    public Map getResponseHeaders(HttpURLConnection httpUrlCon) throws IOException {
        Map map = new HashMap();
        map.put("code", httpUrlCon.getResponseCode());
        map.put("status", httpUrlCon.getResponseCode() + " " + httpUrlCon.getResponseMessage());
        map.put("content-encoding", httpUrlCon.getContentEncoding());
        map.put("content-length", httpUrlCon.getContentLength());
        map.put("content-type", httpUrlCon.getContentType());
        map.put("Date", httpUrlCon.getDate());
        map.put("ConnectTimeout", httpUrlCon.getConnectTimeout());
        map.put("expires", httpUrlCon.getExpiration());
        map.put("content-type", httpUrlCon.getHeaderField("content-type"));
        map.put("WWW-Authenticate", httpUrlCon.getHeaderField("WWW-Authenticate"));

        //利用另一种读取HTTP头字段
        log.info("--------------start-  Response-------------------");
        Map headers = httpUrlCon.getHeaderFields();
        Set<String> keys = headers.keySet();
        for (String key : keys) {
            String val = httpUrlCon.getHeaderField(key);
            log.info(key + ":" + val);
        }
        log.info("---------------over- Response-------------------");
        log.info(JsonUtil.objectToJson(map));
        return map;
    }


    /**
     * 发送get请求
     *
     * @param url   请求地址
     * @param param 请求参数
     * @return
     */
    public String sendGet(String url, String param) {
        log.info("请求参数：[url:{},param:{}]", url, param);
        String result = "";
        BufferedReader in = null;
        try {
            if (StringUtils.isNotBlank(param)) {
                //如果有中文参数，get请求会出现异常，需要对URL进行编码
                url = url + "?" + URLEncoder.encode(param, "utf-8");
                log.info("encodeUrl:{}", url);
            }
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("发送GET请求出现异常！{}" + e);
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                log.error("发送GET请求出现异常！{}" + e2);
            }
        }
        log.info("response: sendmsg = " + param + " return result = " + result + " from sendGet " + " end### ");
        return result;
    }


    public static String get(String url) {
        HttpURLConnection httpUrlCon = null;
        InputStream is = null;
        try {
            URL urlGet = new URL(url);
            httpUrlCon = (HttpURLConnection) urlGet.openConnection();

            httpUrlCon.setRequestMethod("GET");
            httpUrlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpUrlCon.setDoOutput(true);
            httpUrlCon.setDoInput(true);
            httpUrlCon.connect();

            is = httpUrlCon.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            return message;
        } catch (Exception e) {
            return null;
        } finally {
            if (null != httpUrlCon) httpUrlCon.disconnect();
            try {
                if (null != is) is.close();
            } catch (IOException e) {
                log.error("{}", e);
            }
        }
    }

    /**
     * 发送PUT请求
     *
     * @param urlStr url地址
     * @param param  请求参数
     * @return
     */
    public String put(String urlStr, String param) {
        String result = "";
        try {
            log.info("url:{},param:{}", urlStr, param);
            URL url = new URL(urlStr);
            HttpURLConnection httpUrlCon = (HttpURLConnection) url.openConnection();
            httpUrlCon.setRequestMethod("PUT");
            httpUrlCon.setDoInput(true);
            httpUrlCon.setDoOutput(true);
            httpUrlCon.setRequestProperty("Accept-Encoding", "gzip,deflate");
            httpUrlCon.setRequestProperty("Content-Type", "application/*+JSON");
            OutputStream os = httpUrlCon.getOutputStream();
            os.write(param.getBytes("utf-8"));
            os.flush();
            os.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlCon.getInputStream()));
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("PUT请求返回数据：{}", result);
            br.close();
        } catch (Exception e) {
            log.info("Error in pushing policy now");
            log.error("{}", e);
        }
        return result;
    }


}
