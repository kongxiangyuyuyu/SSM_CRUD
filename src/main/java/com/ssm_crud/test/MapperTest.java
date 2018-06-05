package com.ssm_crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm_crud.bean.Department;
import com.ssm_crud.bean.Employee;
import com.ssm_crud.dao.DepartmentMapper;
import com.ssm_crud.dao.EmployeeMapper;

/**
 * 
 * 测试dao层的测试类
 *	spring 的项目可以使用spring的单元测试，可以自动注入我们所需要的组件
 *  1导入springTest的依赖
 *  2contextConfiguration的locations指定spring文件的位置
 *  3直接autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {

	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	 
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * 测试department
	 */
	@Test
	public void testCRUD() {
		
		//原生的方法
		/*
		 
		 1获取ioc容器
		ApplicationContext ioc =new ClassPathXmlApplicationContext("applicationContext.xml");
		 2获取mapper
		Department bean=ioc.getBean(Department.class);
		
		*/
		
		//System.out.println(departmentMapper);
		
		//1 插入几个部门
		
		//departmentMapper.insertSelective(new Department(null, "开发部"));
		//departmentMapper.insertSelective(new Department(null, "测试部"));
		
		//2插入员工信息
		//employeeMapper.insertSelective(new Employee(null, "Marry","M", "123456@qq.com", 1));
		
		//3批量插入多个员工，使用可以批量操作的session
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(null, uid, "M", uid+"996191@qq.com", 1));
		}

		
		System.out.println("批量完成");
	}
}
