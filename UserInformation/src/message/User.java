package message;

/**
 * Created by Administrator on 2016/2/18.
 */
public class User {
    private String uName;

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public int getuScore() {
        return uScore;
    }

    public void setuScore(int uScore) {
        this.uScore = uScore;
    }

    private String uPassword;
    private int uScore;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
    public User(String uName,String uPassword,int uScore){
        this.uName = uName;
        this.uPassword = uPassword;
        this.uScore = uScore;
    }
    public User(){
        super();
    }
}
