package cn.springmvc.spider.gjj;

import cn.springmvc.model.GJJUserInfo;
import cn.springmvc.spider.HttpClientFactory;
import cn.springmvc.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 吉林辽源
 * Created by nidayu on 16/11/28.
 */
@Slf4j
public class LiaoYuan extends HttpClientFactory {

    private CloseableHttpClient httpClient;

    public LiaoYuan(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }


    public GJJUserInfo login(String userName, String password) {

        String url = "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/zfbzsq/login_hidden.jsp?password=" + password + "&sfzh=" +
                userName + "&cxyd=%B5%B1%C7%B0%C4%EA%B6%C8&zgzh=";
        String[][] headers = new String[][]{
                {"Referer", "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/zfbzsq/login.jsp"}
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
                url = "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/zfbzsq/main_menu.jsp?" + name0
                        + "="+ value0 +"&" + name1 + "=" + value1 + "&" + name2 + "=" + URLEncoder.encode(value2, "gb2312") + "&" +
                        name3 + "=" + value3 + "&cxyd=%B5%B1%C7%B0%C4%EA%B6%C8";
            } catch (UnsupportedEncodingException e) {

            }
            responseString = getUrl(url, headers, "GBK");
            if (responseString != null && responseString.contains("公积金信息")) {
                // 解析公积金信息
                return parseGJJBasicPersonaInfo(responseString, userName);
            }
        }
        return null;
    }

    private GJJUserInfo parseGJJBasicPersonaInfo(String responseString, String userName) {
        try {
            GJJUserInfo gjjUserInfo = new GJJUserInfo();
            Document document= Jsoup.parse(responseString);
            Elements tds = document.select("table").get(14).select("tr").select("td");
            gjjUserInfo.setPassword("111111");
            gjjUserInfo.setPersonalAccount(userName);
            if (responseString.contains("该职工已销户")){
                gjjUserInfo.setAccountStatus("该职工已销户");
            }
            for (int i = 1; i < tds.size() - 1; i++){
                Element td = tds.get(i);
                if ("职工姓名".equals(td.text())){
                    gjjUserInfo.setName(tds.get(i + 1).text().replace(" ", ""));
                    continue;
                }
                if ("银行账号".equals(td.text())){
                    gjjUserInfo.setBankCard(tds.get(i + 1).text().replace(" ", ""));
                    continue;
                }
                if ("身份证号".equals(td.text())){
                    gjjUserInfo.setIdCard(tds.get(i + 1).text().replace(" ", ""));
                    continue;
                }
                if ("职工账号".equals(td.text())){
                    gjjUserInfo.setPersonalAccount(tds.get(i + 1).text().replace(" ", ""));
                    continue;
                }
                if ("所在单位".equals(td.text())){
                    gjjUserInfo.setCompanyName(tds.get(i + 1).text().replace(" ", ""));
                    continue;
                }
                if ("所属办事处".equals(td.text())){
                    gjjUserInfo.setOfficeBelone(tds.get(i + 1).text().replace(" ", ""));
                    continue;
                }
                if ("开户日期".equals(td.text())){
                    gjjUserInfo.setDateOpened(DateUtils.stringToDate(tds.get(i + 1).text().replace(" ", ""), "yyyy-MM-dd"));
                    continue;
                }
                if ("当前状态".equals(td.text())){
                    gjjUserInfo.setAccountStatus(tds.get(i + 1).text().replace(" ", ""));
                    continue;
                }
                if ("月缴基数".equals(td.text())){
                    gjjUserInfo.setWageBase(NumberUtils.toDouble(tds.get(i + 1).text().replace(" ", "").replace(",", "")));
                    continue;
                }
                if ("个人/单位".equals(td.text())){
                    String[] ratio = tds.get(i + 1).text().replace(" ", "").split("/");
                    gjjUserInfo.setPersonalDepositRadio(ratio[0]);
                    gjjUserInfo.setCompanyDepositRadio(ratio[1]);
                    continue;
                }
                if ("月缴金额".equals(td.text())){
                    gjjUserInfo.setMonthlyPayment(NumberUtils.toDouble(tds.get(i + 1).text().replace(" ", "").replace(",", "")));
                    continue;}
                if ("上年余额".equals(td.text())){
                    gjjUserInfo.setLastYearBalance(NumberUtils.toDouble(tds.get(i + 1).text().replace(" ", "").replace(",", "")));
                    continue;
                }
                if ("实存总额".equals(td.text())){
                    gjjUserInfo.setAccountBalance(NumberUtils.toDouble(tds.get(i + 1).text().replace(" ", "").replace(",", "")));
                    continue;
                }
            }
            return gjjUserInfo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getFileNames(){
        String path="C:\\gjj\\";
        File file=new File(path);
        File[] tempList = file.listFiles();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < tempList.length; i ++){
            names.add(tempList[i].getName().replace(".html", ""));
        }
        return names;
    }

    public static void main(String[] args) {

        CloseableHttpClient httpClient = getInstance();
        LiaoYuan liaoYuan = new LiaoYuan(httpClient);



//        String first = "01";
//        for (int j = 1527; j < 100000; j++){ //
//            String begin = "0000" + j;
//            begin = begin.substring(begin.length() - 5);
//            int total = 0;
//            no : for (int i = 1; i < 10000 ; i++) {
//                String end = "00000" + i;
//                end = end.substring(end.length() - 6);
//                int index = liaoYuan.login(first + begin + end, "111111");
//                if (index == 0){
//                    // 有数据
//                    total = 0;
//                } else {
//                    // 无数据
//                    total = total + 1;
//                }
//                if (total > 30){
//                    // 连续30个没有数据
//                    break no;
//                }
//            }
//        }
        // 取年
//        for (int year = 1970; year < 1995; year ++) {
//            // 取月
//            for (int mon = 1; mon < 13; mon ++){
//                String month = mon < 10 ? ("0"+mon) : mon + "";
//                // 取日
//                int lastDay = 31;
//                if (mon == 2){
//                    lastDay = 28;
//                } else if (mon == 4 || mon == 6 || mon ==9 || mon == 11){
//                    lastDay = 30;
//                }
//                for (int day = 1; day <= lastDay; day ++){
//                    String thisDay = day < 10 ? ("0" + day) : day + "";
//                    for (int last = 1; last < 10000; last++) {
//                        String four = "000" + last;
//                        four = four.substring(four.length() - 4);
//                        String info = first + year + month + thisDay + four;
//                        System.out.println(info);
//                        liaoYuan.login(info, "111111");
//                        try {
//                            Thread.sleep(3 * 1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
    }

}
