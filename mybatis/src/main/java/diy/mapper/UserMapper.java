package diy.mapper;

import diy.annotation.Select;
import diy.entity.UserEntity;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where id = ?")
    UserEntity getUserById(Long id);

    @Select("select * from user")
    List<UserEntity> selectAllUser();
}
