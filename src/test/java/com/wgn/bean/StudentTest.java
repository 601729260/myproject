package com.wgn.bean;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.wgn.dao.StudentMapper;

public class StudentTest {
	SqlSession session;
	@Before
	public void beforeLoadXml(){
		InputStream inputstream=MybatisTest.class.getClassLoader().getResourceAsStream("Configuration.xml");
		SqlSessionFactory sqlsessionfactory=new SqlSessionFactoryBuilder().build(inputstream);
		session=sqlsessionfactory.openSession();
		
	}
	@Test
	public void testAnnotation()
	{
		StudentMapper mapper=session.getMapper(StudentMapper.class);
		Student t=new Student();
		t.setId(3);
		t.setName("haha");
		session.commit();
		session.close();
	}
}
