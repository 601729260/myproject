package com.wgn.bean;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.wgn.annocation.TeacherMapper;

public class TeacherTest {
	SqlSession session;
	@Before
	public void beforeLoadXml(){
		InputStream inputstream=MybatisTest.class.getClassLoader().getResourceAsStream("mybatis/Configuration.xml");
		SqlSessionFactory sqlsessionfactory=new SqlSessionFactoryBuilder().build(inputstream);
		session=sqlsessionfactory.openSession();
		
	}
	@Test
	public void testAnnotation()
	{
		TeacherMapper mapper=session.getMapper(TeacherMapper.class);
		Teacher t=new Teacher();
		TeacherExample te=new TeacherExample();
		te.createCriteria().andNameEqualTo("wgn");
		List<Teacher> listTeacher=mapper.selectByExample(te);
		Iterator iter=listTeacher.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		session.commit();
		session.close();
	}
}
