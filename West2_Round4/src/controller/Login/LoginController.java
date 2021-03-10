package controller.Login;

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
@RequestMapping("/login")
public class LoginController {

    //设置恒定变量、数据库链接
    public static final String URL = "jdbc:mysql://localhost:3306/west2?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    static Connection conn = null;

    //跳转到注册页面
    @RequestMapping("/goRegister")
    public String goRegister() {
        return "register";
    }

    //执行登陆请求
    //需求变量：帐号、密码
    @RequestMapping(value = "/doLogin")
    public ModelAndView register(@RequestParam(required = true) int account, @RequestParam(required = true) String password) {
        ModelAndView mv = new ModelAndView();
        //创建链接
        try {
            conn = getConnection();
            System.out.println(conn);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //数据库代理
        UserDao userDao = new UserDao();
        User target = userDao.findAccount(account);
        boolean result = userDao.verifyUser(account, password);
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //判断账号是否存在于数据库
        if(target!=null){
            System.out.println("Account is found.");
            if (result) {
                //判断密码是否匹配
                System.out.println("Password is matched");
                boolean administrator = target.getAdministrator();
                mv.addObject("account", account);
                mv.addObject("password", password);
                mv.addObject("username", target.getUsername());
                mv.addObject("userID", target.getUserID());
                //根据是否是管理员 抉择跳转的目标页面
                if (administrator) {
                    System.out.println("Welcome administrator:"+target.getUsername());
                    mv.setViewName("administrator");
                } else {
                    System.out.println("Welcome user:"+target.getUsername());
                    mv.setViewName("user");
                }
            } else {
                //账号密码不匹配 、 账号存在
                System.out.println("Password is not matched.");
                mv.setViewName("login_WithoutMatched");
            }
        }
        else{
            //账号不存在
            System.out.println("Account does not exist.");
            mv.setViewName("login_WithoutAccount");
        }
        return mv;
    }
}
