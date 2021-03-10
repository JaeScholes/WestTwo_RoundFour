package controller.userPicture;

import config.MultipartFile;
import dao.UncheckedFileDao;
import database.UncheckedFile;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import static database.DbUtil.getConnection;

@Controller
public class UserPictureController {

    //设置恒定变量、路径
    public static final String URL = "jdbc:mysql://localhost:3306/west2?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    static Connection conn = null;
    public static final String userPath = "Files\\";

    private boolean saveFile(MultipartFile file, String path) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                File filepath = new File(path);
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

    @RequestMapping(value = "/goUser")
    public String goUser(){
        return "user";
    }

    //下载文件
    @RequestMapping(value = "/download")
    public void downloadFile(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response,@RequestParam int account){
        if(fileName != null){
            String realPath = request.getSession().getServletContext().getRealPath(userPath+"//"+account);
            File file = new File(realPath, fileName);
            OutputStream out = null;
            if(file.exists()){
                //设置下载完毕不打开文件
                response.setContentType("application/force-download");
                //设置文件名
                response.setHeader("Content-Disposition", "attachment;filename="+fileName);
                try {
                    out = response.getOutputStream();
                    //使用工具类
                    out.write(FileUtils.readFileToByteArray(file));
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    if(out != null){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @RequestMapping("/delFile")
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



}
