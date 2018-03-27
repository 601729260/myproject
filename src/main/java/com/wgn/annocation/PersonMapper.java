package com.wgn.annocation;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wgn.bean.Person;

public interface PersonMapper {
	@Insert("insert into person(name,id) values(#{name},#{id})")
	public int addPerson(Person person);
	@Select("select * from person where id=#{id}")
	public Person selectById(Person person);
	@Update("update person set name=#{name} where id=#{id}")
	public int updatePerson(Person person);
	@Delete("delete from person where id=#{id}")
	public int deletePerson(Person person);

}
