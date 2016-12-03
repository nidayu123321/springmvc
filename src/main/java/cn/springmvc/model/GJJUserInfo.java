package cn.springmvc.model;

import java.util.Date;

/**
 * Created by nidayu on 16/12/3.
 */
public class GJJUserInfo {

    private int id;
    // 姓名
    private String name;
    // 单位名称
    private String companyName;
    // 个人公积金帐号
    private String personalAccount;
    // 单位公积金帐号
    private String companyAccount;
    // 身份证号码
    private String idCard;
    // 公积金卡号
    private String cardNumber;
    // 申报工资基数 or 月缴基数
    private Double wageBase;
    // 单位缴存比例
    private String companyDepositRadio;
    // 个人缴存比例
    private String personalDepositRadio;
    // 职工月缴额
    private Double personalMonthlyPayment;
    // 单位月缴额
    private Double companyMonthlyPayment;
    // 账户余额
    private Double accountBalance;
    // 账户状态
    private String accountStatus;
    // 承办银行
    private String joinBank;
    // 归集点
    private String collectionPoint;

    // 银行账号
    private String bankCard;
    // 密码
    private String password;
    // 所属办事处
    private String officeBelone;
    // 开户日期
    private Date dateOpened;
    // 月缴基数 = 个人缴存比例 + 单位缴存比例
    private Double monthlyPayment;
    // 上年余额
    private Double lastYearBalance;
}
