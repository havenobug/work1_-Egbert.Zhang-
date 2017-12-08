package com.zy.Service;

import com.zy.entity.Message;

import java.util.List;
//自定义方法
public interface MessageService {
    public List<Message> getUserList();

    public Message findUserById(long id);

    public void save(Message message);

    public void edit(Message message);

    public void delete(long id);
}
