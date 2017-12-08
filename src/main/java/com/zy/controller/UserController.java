package com.zy.controller;

import com.zy.dao.UserDao;
import com.zy.entity.UserEntity;
import com.zy.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    //index页面
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //修改密码页面
    @RequestMapping("/forgetpassword")
    public String forgetpasswrod(){
        return "forgetpassword";
    }

    //注册页面
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    //登录页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //修改密码 验证账户
    @RequestMapping("/editpassword")
    public String forgetpassword(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
//        int mobile = Integer.parseInt(request.getParameter("mobile"));
        String username = request.getParameter("username");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String mobile1 = "123";
        UserEntity userentity = userDao.findByUsername(username);
        String str = "";
        if(userentity != null){
            if (mobile.equals(mobile1)) {
                userentity.setPassword(password);
                userDao.save(userentity);
                out.print("<script language=\"javascript\">alert('密码修改成功')</script>");
                str = "login";
            }
            else {
                out.print("<script language=\"javascript\">alert('验证码输入错误')</script>");
                str = "forgetpassword";
            }
        }
        else {
            out.print("<script language=\"javascript\">alert('对不起，没有找到您的账号')</script>");
            str = "forgetpassword";
        }

        return str;
    }


    //注册方法 添加校验
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        String password2 = request.getParameter("password2");
        UserEntity userEntity = new UserEntity();
        UserEntity userentity1 = userDao.findByUsername(username);  //从数据库中查找username
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        String str = "";
        if (userentity1 == null) {              //寻找当前的账户中 是否重复 如果重复 跳转到登陆页面
            if (username !=null && password !=null) {
                userEntity.setUsername(username);
                userEntity.setFailnum(0);
                userEntity.setPassword(Md5.getMD5(password));
                userDao.save(userEntity);
                str = "login";
            }
            else {
                out.print("<script language=\"javascript\">alert('账号或密码不能为空')</script>");
            }
        } else {
            out.print("<script language=\"javascript\">alert('此号已经被注册了')</script>");
            str = "login";
        }
        return str;
    }
/*

 */

    //登录方法

    @RequestMapping("/addlogin")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(password);
        UserEntity userEntity1 = userDao.findByUsername(username);
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        String str = "";
        int failnum = 0;
        Date nowDate = new Date();
        System.out.println(nowDate);
        String password1 = "";

        if (userEntity1 != null) {
            failnum = userEntity1.getFailnum();
            password1 = userEntity1.getPassword(); //获取相应username下的password，对从前段传来的password 进行比较
            System.out.println(password1);
            if (password1.equals(password)) { //把username下相应的密码取出 与输入的密码进行对比
                failnum = userEntity1.getFailnum(); //取出failnum
                if (failnum<3){
                    userEntity1.setFailnum(0);
                    userDao.save(userEntity1);
                    str = "redirect:/list";
                }
                /*
                **当failnum不小于3时，
                * 获取当前时间和上次被冻结时间进行对比，如果大于一天，且密码输入正确，顺利登陆
                 */
                else {
                    nowDate = userEntity1.getLogindate();
                    Date nowDate1 = new Date();
                    if (nowDate1.getTime()-nowDate.getTime()>60*60*24*1000){ //将最后一次登录的时间与当前时间进行对比 如果相差大于24h，则解冻
                            userEntity1.setFailnum(0);
                            userDao.save(userEntity1);
                            str = "redirect:/list";
                    }
                    else {
                        out.print("<script language=\"javascript\">alert('此号被冻结，24h不能登陆')</script>");
                        str ="login";
                    }
                }
            }


            else {  //如果密码输入错误 则fail依次加一 当大于三次后不能登陆
                failnum += 1;
                userEntity1.setFailnum(failnum);
                userDao.save(userEntity1);
                System.out.println(failnum);
                if (failnum == 3) { // 判断failnum数是否为3 如果是 则存入当前时间
                    Date date = new Date();
                    userEntity1.setLogindate(date);
                    userDao.save(userEntity1);
                    out.print("<script language=\"javascript\">alert('此号被冻结，24h不能登陆')</script>");
                    str ="login";
                }
                else {
                    if (failnum > 3){
                        out.print("<script language=\"javascript\">alert('此号被冻结，24h不能登陆')</script>");
                        str ="login";
                    }
                    else {
                        out.print("<script language=\"javascript\">alert('密码错误')</script>");
                        str="login";
                    }

                }

            }
//                userEntity1.setFailnum(failnum);
//                userDao.save(userEntity1);
            // str = "login";
        }
//        else if(userEntity1 == null){
//            out.print("<script language=\"javascript\">alert('此号还没有注册哟')</script>");
//            str ="redirect:/register";
//        }
        else{
            out.print("<script language=\"javascript\">alert('此用户还未注册')</script>");
            str ="redirect:/login";
        }
        return str;
    }


}
