package com.demo.selenium.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demo.selenium.models.User;

public interface UserMapper {
	  @Select("SELECT * FROM user WHERE id=#{userId}")
	  public User getByID(@Param("userId") Integer userId);
	  
	  @Select("SELECT count(1) FROM user")
	  public Integer getCount();

	  @Select("SELECT * FROM user")
	  public User getAll();
	  

	  @Insert("insert into user (name,pwd,uniqueID,createdUser,createdDate) values (#{name},#{pwd},uuid(),#{createdUser},now())")
	  public Integer create(User user);
}
