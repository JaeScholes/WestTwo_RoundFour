package controller.Register;

import dao.UserDao;
import database.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.SQLException;

import static database.DbUtil.getConnection;

@Controller
public class RegisterController {

    //设置恒定变量、数据库链接
    public static final String URL = "jdbc:mysql://localhost:3306/west2?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    static Connection conn = null;


    //跳转登陆页面的请求
    @RequestMapping("/goLogin")
    public String goLogin(){
        return "login";
    }

    //执行注册的请求
    //需求变量：用户名、密码、第二次输入的密码
    @RequestMapping("/doRegister")
    public ModelAndView doRegister(@RequestParam(value = "username",required = true) String username, @RequestParam(value = "password",required = true) String password,
                                   @RequestParam(value = "rpassword",required = true) String rpassword ){
        //创建链接
        ModelAndView mv = new ModelAndView();
        try {
            conn = getConnection();
            System.out.println(conn);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //密码和确认密码是否一致
        if(!rpassword.equals(password)){
            System.out.println("Password is different from the other.");
            mv.addObject("bug",1);
            mv.setViewName("register");
        }
        //判断是否密码长度超过8
        else{
            if(password.length()<8){
                System.out.println("Password is too short.");
                mv.addObject("bug",2);
                mv.setViewName("register");
            }
            else{
                //判断是否用户昵称长度超过5
                if(username.length()>5){
                    System.out.println("Username is too long.");
                    mv.addObject("bug",3);
                    mv.setViewName("register");
                }
                else{
                    //数据库代理
                    UserDao userDao = new UserDao();
                    boolean result = userDao.findUsername(username);
                    //判断是否用户名被占用
                    if(result){
                        System.out.println("Username has been occupied.");
                        mv.setViewName("register");
                    }
                    //传入数据
                    else {
                        User user = new User(password,username);
                        userDao.addUser(user);
                        mv.addObject("username",username);
                        mv.addObject("account",user.getAccount());
                        mv.addObject("password",password);
                        //返回欢迎页面
                        mv.setViewName("welcome");
                    }
                }
            }
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mv;
    }





}
