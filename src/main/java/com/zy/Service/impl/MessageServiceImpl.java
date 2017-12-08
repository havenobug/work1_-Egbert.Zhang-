package com.zy.Service.impl;

import com.zy.Service.MessageService;
import com.zy.dao.MessageRepository;
import com.zy.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    //对userservice中的方法进行实现
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getUserList() {
        return messageRepository.findAll();
    }

    @Override
    public Message findUserById(long id) {
        return messageRepository.findById(id);
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void edit(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void delete(long id) {
        messageRepository.delete(id);
    }

}
