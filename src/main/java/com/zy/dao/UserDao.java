package com.zy.dao;

import com.zy.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserEntity,Long>{
    public UserEntity findByUsernameAndPassword(String username,String password);
    public UserEntity findByUsername(String username);
    public UserEntity findByPassword(String password);
}
