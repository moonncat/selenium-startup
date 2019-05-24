package com.demo.selenium;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.demo.selenium.mapper.UserMapper;
import com.demo.selenium.models.User;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.session.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Component
public class MysqlDataService {

	@Autowired
	private SqlSessionFactory ssf;
	@Value("${dblink}")
	private String dblink;
	
	public MapperFactoryBean<UserMapper> getUserMapperFactoryBean() {

    	MapperFactoryBean<UserMapper> mfb=new MapperFactoryBean<UserMapper>();
    	mfb.setSqlSessionFactory(ssf);
    	return mfb;
	}

	
	public User getUser() throws Exception{

		User u;
        try {

        	MysqlDataSource mds=new MysqlDataSource();
        	mds.setURL(this.dblink);

        	Connection con= mds.getConnection();
        	
        	Statement sttm= con.createStatement();
        	ResultSet rs= sttm.executeQuery("SELECT id,name FROM user WHERE id >1");
        	u=new User();
        	rs.next();
        	u.id=rs.getString("id");
        	u.name=rs.getString("name");
			
        }catch(Exception ex) {
        	throw ex;
        }
		return u;
	} 
	public User getByID(Integer userID) throws Exception{

		User u;
        try {
			SqlSession ss=this.getUserMapperFactoryBean().getSqlSession();
			u=(User)ss.selectOne("getByID",userID);
			
        }catch(Exception ex) {
        	throw ex;
        }
		return u;
	}

	  public List<User> getAll() {

		  List<User> users;
	        try {
				SqlSession ss=this.getUserMapperFactoryBean().getSqlSession();
				users=ss.selectList("getAll");
				
	        }catch(Exception ex) {
	        	throw ex;
	        }
			return users;
	  }
		public Integer create(User user) throws Exception{

			Integer rc;
	        try {
				SqlSession ss=this.getUserMapperFactoryBean().getSqlSession();
				rc=ss.insert("create",user);
				
	        }catch(Exception ex) {
	        	throw ex;
	        }
			return rc;
		}

}
