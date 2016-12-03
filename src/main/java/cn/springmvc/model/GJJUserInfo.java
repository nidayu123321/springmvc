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

    public Double getLastYearBalance() {
        return lastYearBalance;
    }

    public void setLastYearBalance(Double lastYearBalance) {
        this.lastYearBalance = lastYearBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(String personalAccount) {
        this.personalAccount = personalAccount;
    }

    public String getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(String companyAccount) {
        this.companyAccount = companyAccount;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getWageBase() {
        return wageBase;
    }

    public void setWageBase(Double wageBase) {
        this.wageBase = wageBase;
    }

    public String getCompanyDepositRadio() {
        return companyDepositRadio;
    }

    public void setCompanyDepositRadio(String companyDepositRadio) {
        this.companyDepositRadio = companyDepositRadio;
    }

    public String getPersonalDepositRadio() {
        return personalDepositRadio;
    }

    public void setPersonalDepositRadio(String personalDepositRadio) {
        this.personalDepositRadio = personalDepositRadio;
    }

    public Double getPersonalMonthlyPayment() {
        return personalMonthlyPayment;
    }

    public void setPersonalMonthlyPayment(Double personalMonthlyPayment) {
        this.personalMonthlyPayment = personalMonthlyPayment;
    }

    public Double getCompanyMonthlyPayment() {
        return companyMonthlyPayment;
    }

    public void setCompanyMonthlyPayment(Double companyMonthlyPayment) {
        this.companyMonthlyPayment = companyMonthlyPayment;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getJoinBank() {
        return joinBank;
    }

    public void setJoinBank(String joinBank) {
        this.joinBank = joinBank;
    }

    public String getCollectionPoint() {
        return collectionPoint;
    }

    public void setCollectionPoint(String collectionPoint) {
        this.collectionPoint = collectionPoint;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOfficeBelone() {
        return officeBelone;
    }

    public void setOfficeBelone(String officeBelone) {
        this.officeBelone = officeBelone;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
