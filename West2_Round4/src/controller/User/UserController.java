package controller.User;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.*;
import config.MultipartFile;
import dao.UncheckedFileDao;
import database.UncheckedFile;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import static database.DbUtil.getConnection;

@Controller
public class UserController {

    //设置恒定变量、路径
    public static final String URL = "jdbc:mysql://localhost:3306/west2?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    static Connection conn = null;
    public static final String userPath = "Files\\";
    public static final String adPath = "Files\\UncheckedFiles";

    //跳转到登陆页面请求
    @RequestMapping("/goLogin")
    public String goLogin(){
        return "login";
    }

    //保存文件
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

    //创建文件夹
    @RequestMapping("/createFolder")
    public String createFolder(@RequestParam(required = true) String foldername, @RequestParam(required = true) String folderplace){
        String path = folderplace+"\\"+foldername;
        File folder = new File(path);
        folder.mkdir();
        return "user";
        }

     //上传文件
    //需求变量：文件本身、执行操作的用户账号、储存于网盘中的目标路径
    @RequestMapping("/uploadFiles")
    public String uploadFile(@RequestParam("files") MultipartFile[] files,@RequestParam(required = true) int account,@RequestParam(required = true) String path){
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
        //判断file数组不能为空并且长度大于0
        if(files!=null&&files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                //保存文件
                UncheckedFile afile = new UncheckedFile(account,path,adPath,file.getContentType());
                dao.addUncheckedFile(afile);
                saveFile(file, userPath+"\\"+account);
            }
        }
        // 重定向
        return "user";
    }

    //删除目标文件
    //需求变量：目标文件路径、帐号
    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestParam(required = true) String path){
        File target = new File(path);
        if(target.exists()){
            target.delete();
        }
        else {
            System.out.println("File does not exist");
        }
        return "user";
    }

    //下载文件
    @RequestMapping(value = "/download")
    public void downloadFile(@RequestParam(required = true) String fileName, HttpServletRequest request, HttpServletResponse response){
        if(fileName != null){
            String realPath = request.getSession().getServletContext().getRealPath("file/");
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


}
