package cn.springmvc.model;

/**
 * @author nidayu
 * @Description:
 * @date 2015/8/11
 */
public class User {
    private int id;
    private String password;
    private String cardNo;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
