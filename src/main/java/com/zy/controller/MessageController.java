package com.zy.controller;

import com.zy.Service.MessageService;
import com.zy.entity.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MessageController {
    @Resource
    private MessageService messageService;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Message> messages = messageService.getUserList();
        model.addAttribute("messages", messages);
        return "user/list";
    }

    //增加留言
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(Message message) {
        messageService.save(message);
        return "redirect:/list";
    }

    //根据id进行修改留言
    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        Message message = messageService.findUserById(id);
        model.addAttribute("message", message);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(Message message) {
        messageService.edit(message);
        return "redirect:/list";
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        messageService.delete(id);
        return "redirect:/list";
    }
}
