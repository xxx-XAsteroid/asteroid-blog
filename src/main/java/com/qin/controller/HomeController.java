package com.qin.controller;

import com.alibaba.druid.util.StringUtils;
import com.qin.entity.Blog;
import com.qin.entity.R;
import com.qin.entity.User;
import com.qin.service.BlogService;
import com.qin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/user/{id}")
    public String user(@PathVariable("id")Integer id){
        // 如果用户不存在，跳转到首页
        User user = userService.getUserById(id);
        if(null == user){
            return "/";
        }

        return "home";
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request){
        String user = userService.getUser(request);

        // 当前账号未登录就跳转到首页
        if(StringUtils.isEmpty(user)){
            return "/";
        }

        return "home";
    }

    @RequestMapping("/user/{id}/blogs")
    @ResponseBody
    public R getUserBlog(@PathVariable("id")Integer id){
        List<Blog> blogs = blogService.getBLogsByUserId(id);
        return R.ok(blogs);
    }
}
