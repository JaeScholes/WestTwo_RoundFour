package database;

//未验证文件类
public class UncheckedFile {

    //储存的用户账号
    private int account;
    //用户想把文件储存的路径
    private String targetPath;
    //目前该文件处于管理员文件夹中的路径
    private String presentPath;
    //文件类型
    private String type;

    //创建方法
    public UncheckedFile(){
        account=-1;
        targetPath="";
        presentPath="";
        type="";
    }

    //创建方法
    public UncheckedFile(int account, String targetPath, String presentPath, String type){
        this.account=account;
        this.targetPath=targetPath;
        this.presentPath=presentPath;
        this.type=type;
    }

    //get和set方法
    public int getAccount() {
        return account;
    }

    public String getPresentPath() {
        return presentPath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setPresentPath(String presentPath) {
        this.presentPath = presentPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
