package com.wgn.bean;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.wgn.annocation.PersonMapper;

public class AnnotationTest {
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
		PersonMapper mapper=session.getMapper(PersonMapper.class);
		Person p=new Person();
		p.setId(3);
		p.setName("haha");
		mapper.addPerson(p);
		p.setId(4);
		p.setName("haha4");
		mapper.updatePerson(p);
		p.setId(2);
		mapper.selectById(p);
		p.setId(5);
		mapper.deletePerson(p);
		session.commit();
		session.close();
	}

}
