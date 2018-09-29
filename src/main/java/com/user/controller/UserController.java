package com.user.controller;

import com.basic.vo.Result;
import com.user.service.UserService;
import com.user.vo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @RequestMapping(value = "user", method = {RequestMethod.GET})
    public ModelAndView user(User user) {
        ModelAndView mav = new ModelAndView();
        if (user.getUserId() != null) {
            user = userService.get(user);
            mav.addObject("user", user);
        }
        mav.setViewName("user/user");
        return mav;
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    @ResponseBody
    public Result add(User user) {
        try {
            userService.add(user);
            return new Result();
        } catch (Exception e) {
            log.error(e);
            return new Result("保存失败");
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Result delete(User user) {
        try {
            userService.delete(user);
            return new Result();
        } catch (Exception e) {
            log.error(e);
            return new Result("删除失败");
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public Result update(User user) {
        try {
            userService.update(user);
            return new Result();
        } catch (Exception e) {
            log.error(e);
            return new Result("更新失败");
        }
    }

    @RequestMapping("list")
    public ModelAndView list(User user) {
        ModelAndView mav = new ModelAndView();
        List<User> list = userService.list(user);
        mav.addObject("list", list);
        mav.addObject("user", user);
        mav.setViewName("user/list");
        return mav;
    }

    @RequestMapping("list2")
    @ResponseBody
    public List<User> list2(User user) {
        List<User> list = userService.list(user);
        return list;
    }

}
