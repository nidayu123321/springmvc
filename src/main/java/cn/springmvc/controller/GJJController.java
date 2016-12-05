package cn.springmvc.controller;

import cn.springmvc.model.GJJUserInfo;
import cn.springmvc.service.GJJUserInfoService;
import cn.springmvc.spider.HttpClientFactory;
import cn.springmvc.spider.gjj.HeFei;
import cn.springmvc.spider.gjj.LiaoYuan;
import cn.springmvc.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private GJJUserInfoService gjjUserInfoService;

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
    public String getLiaoYuanGJJUserInfo(){
        log.info("辽源");
        List<GJJUserInfo> gjjUserInfoList = new ArrayList<>();
        // 根据文本进行抓取
        String text = FileUtil.readFile("c://辽源.htm");
        String[] names = text.split("\\n");
        // 查询出已经存在的
        List existAccounts = gjjUserInfoService.queryExistAccount();
        StringBuffer stringBuffer = new StringBuffer();
        CloseableHttpClient httpClient = HttpClientFactory.getInstance();
        LiaoYuan liaoYuan = new LiaoYuan(httpClient);
        for (int i = 0; i < names.length; i ++){
            String name = names[i];
            name = name.replace("\r", "");
            if (existAccounts == null || !existAccounts.contains(name)) {
                GJJUserInfo gjjUserInfo = liaoYuan.login(name, "111111");
                if (gjjUserInfo != null) {
                    gjjUserInfoList.add(gjjUserInfo);
                    int flag = gjjUserInfoService.insertGJJUserInfo(gjjUserInfo);
                    if (flag != 1) {
                        stringBuffer.append(name + "\\n");
                    }
                }
            }
            if (gjjUserInfoList.size() >= 100 || i == names.length - 1){
                int flag = gjjUserInfoService.batchInsetGJJUserInfo(gjjUserInfoList);
                log.info("flag is......" + flag);
                gjjUserInfoList.clear();
            }
        }
        log.info(stringBuffer.toString());
        return stringBuffer.toString();
    }

}
