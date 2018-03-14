package com.wgn.bean;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class MybatisTest {
	SqlSession session;
	
	@Before
	public void beforeLoadXml(){
		InputStream inputstream=MybatisTest.class.getClassLoader().getResourceAsStream("mybatis/Configuration.xml");
		SqlSessionFactory sqlsessionfactory=new SqlSessionFactoryBuilder().build(inputstream);
		session=sqlsessionfactory.openSession();
		
	}
	@Test
	public void testSelectById(){
		String statement="com.wgn.bean.personMapper.selectPersonById";
		Person person=session.selectOne(statement, 1);
		System.out.println(person);
		session.close();
	}
	@Test 
	public void testSelectAll(){
		String statement="com.wgn.bean.personMapper.getAllPerson";
		List<Person> listPerson=session.selectList(statement);
		System.out.println(listPerson);
		session.close();
	}

	@Test
	public void testUpdate(){
		String statement="com.wgn.bean.personMapper.updatePersonById";
		Person person=new Person();
		person.setId(1);
		person.setName("wgn-helloworld");
		session.update(statement, person);
		session.commit();
		session.close();
	}
	@Test
	public void testAdd(){
		String statement="com.wgn.bean.personMapper.addPerson";
		Person person=new Person();
		person.setId(1);
		person.setName("lihp");
		session.insert(statement, person);
		session.commit();
		session.close();	
	}
	@Test
	public void testDel(){
		String statement="com.wgn.bean.personMapper.deletePersonById";
		Person person=new Person();
		person.setId(2);
		session.delete(statement, person);
		session.commit();
		session.close();	
	}
}
