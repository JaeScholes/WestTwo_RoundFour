package database;

//用户类
public class User {

    private int userID;
    private int account;
    private String password;
    private String username;
    private static int numberUser=0;
    //判断是否为管理员
    private boolean administrator;

    //创建方法
    public User(){
        numberUser++;
        userID = numberUser;
        username="";
        password="";
        account=0;
        administrator=false;
    }

    //创建方法（利用昵称和密码）
    public User(String password, String nickname){
        this.username=nickname;
        this.password=password;
        numberUser++;
        userID = numberUser;
        this.account=numberUser;
        administrator=false;
    }

    //创建方法（利用帐号和密码）
    public User(int account, String password){
        this.account=account;
        this.password=password;
        numberUser++;
        userID = numberUser;
    }


    //get和set方法
    public int getUserID() {
        return userID;
    }

    public int getNumber() {
        return numberUser;
    }

    public int getAccount() {
        return account;
    }

    public boolean getAdministrator(){ return administrator; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(int userID) { this.userID = userID; }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setUsername(String nickname) {
        this.username = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdministrator(boolean administrator) { this.administrator = administrator;}


    //打印信息
    public  void printInformation(){
       String information="ID: "+userID+"\nUsername: "+username+"\nAccount: "+account+"\nPassword: "+password+"\nAdministrator: "+administrator;
       System.out.println(information);
    }

}
