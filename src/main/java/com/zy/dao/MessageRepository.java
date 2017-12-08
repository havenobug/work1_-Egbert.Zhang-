package com.zy.dao;

import com.zy.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
//继承自jpa，实现一组jpa规范相关的方法
public interface MessageRepository extends JpaRepository<Message, Long> {
                Message findById(long id);
                Long deleteById(Long id);
}
