package cn.springmvc.controller;

import cn.springmvc.service.UserService;
import cn.springmvc.spider.HttpClientFactory;
import cn.springmvc.spider.gjj.HeFei;
import cn.springmvc.spider.gjj.LiaoYuan;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author nidayu
 * @Description:
 * @date 2016/11/28
 */
@Slf4j
@Controller
@RequestMapping("/gjj/")
public class GJJController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "hefei")
    public String clawHeFei(){
        log.info("合肥......");
        CloseableHttpClient httpClient = HttpClientFactory.getInstance();
        HeFei heFei = new HeFei(httpClient);
        return "liaoyuan";
    }

    /**
     * 吉林辽源
     * @return
     */
    @RequestMapping(value = "liaoyuan")
    public String queryUserById(){
        log.info("开始抓取辽源");
        CloseableHttpClient httpClient = HttpClientFactory.getInstance();
        LiaoYuan liaoYuan = new LiaoYuan(httpClient);
        String first = "01";
        for (int j = 1210; j < 100000; j++){ //730以上 677开始往下
            String begin = "0000" + j; //01 00001 000001
            begin = begin.substring(begin.length() - 5);
            int total = 0;
            no : for (int i = 1; i < 10000 ; i++) {
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
                if (total > 10){
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

        return "liaoyuan";
    }

}
