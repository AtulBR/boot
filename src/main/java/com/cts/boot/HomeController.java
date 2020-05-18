package com.cts.boot;

import com.cts.Model.User;
import com.cts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String viewHomePage(Model m) {
        List<User> userList = userService.getAllUsers();
        m.addAttribute("userList",userList);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewUserPage(Model m) {
        User user = new User();
        m.addAttribute("user",user);
        return "new_user";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name="id") int id) {
        ModelAndView mv = new ModelAndView("edit_user");
        User user = userService.getUserById(id);
        mv.addObject("user", user);
        return mv;
    }

    @RequestMapping("delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}