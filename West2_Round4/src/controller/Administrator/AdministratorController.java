package controller.Administrator;

import config.MultipartFile;
import dao.UncheckedFileDao;
import dao.UserDao;
import database.UncheckedFile;
import database.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static database.DbUtil.getConnection;

@Controller
public class AdministratorController {

    //配置恒定变量用于 路径设置 以及 数据库连接
    public static final String URL = "jdbc:mysql://localhost:3306/west2?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    static Connection conn = null;
    public static final String adPath = "Files\\UncheckedFiles";
    public static final String userPath = "Files\\";


    //保存文件方法
    private boolean saveFile(MultipartFile file, String path) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                //根据路径创建File对象
                File filepath = new File(path);
                //如不存在则创建文件夹
                if (!filepath.exists())
                    filepath.mkdirs();
                // 文件保存路径
                String savePath = path + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(savePath));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    //跳转登陆页面请求
    @RequestMapping("/goLogin")
    public String goRegister() {
        return "login";
    }

    //管理员表现未审核通过的文件请求
    @RequestMapping("/showUncheckedFiles")
    public ModelAndView showUncheckedFiles(){
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
        //创建 未审核文件的dao对象
        UncheckedFileDao dao = new UncheckedFileDao();
        //展现所有未审核文件
        List<UncheckedFile> result = dao.findUncheckedFile();
        if(!result.isEmpty()) {
            mv.addObject("files", result);
        }
        //返回对应jsp页面
        mv.setViewName("administrator");
        return mv;
    }

    //审验文件功能请求
    @RequestMapping("/CheckFile")
    public String CheckFile(@RequestParam(required = true) String presentPath) throws SQLException {
        try {
            conn = getConnection();
            System.out.println(conn);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //创建 未审核文件的dao对象
        UncheckedFileDao dao = new UncheckedFileDao();
        //根据储存于管理员文件系统来创建File对象
        UncheckedFile file = dao.findUncheckedFile(presentPath);
        dao.delUncheckedFile(presentPath);
        File target = new File(presentPath);
        //移动文件
        target.renameTo(new File(file.getTargetPath() + target.getName()));
        return "administrator";
    }

    //删除不合格的文件
    //需求变量 该不合格文件在管理员文件夹中的路径
    @RequestMapping("/delUncheckedFile")
    public String delUncheckedFile(@RequestParam(required = true) String presentPath) throws SQLException {
        try {
            conn = getConnection();
            System.out.println(conn);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        UncheckedFileDao dao = new UncheckedFileDao();
        UncheckedFile file = dao.findUncheckedFile(presentPath);
        dao.delUncheckedFile(presentPath);
        File target = new File(presentPath);
        target.delete();
        return "administrator";
    }

    //删除用户
    //需求变量：目标用户的帐号
    @RequestMapping("/delAccount")
    public String delAccount(@RequestParam(required = true) int account) throws SQLException {
        try {
            conn = getConnection();
            System.out.println(conn);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        UncheckedFileDao uncheckedFileDaodao = new UncheckedFileDao();
        uncheckedFileDaodao.delUncheckedFile(account);
        UserDao userDao = new UserDao();
        User targetuser = userDao.findAccount(account);
        userDao.delUser(targetuser);
        File targetUnchecked = new File(adPath+""+account);
        targetUnchecked.delete();
        File targetChecked = new File(userPath+""+account);
        targetChecked.delete();
        return "administrator";
    }

}
