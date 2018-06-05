package com.ssm_crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm_crud.bean.Employee;
import com.ssm_crud.bean.Msg;
import com.ssm_crud.service.EmployeeService;

/**
 * 处理员工CRUD请求
 * 
 * @author dell
 *
 */

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * 单个批量二合一的方法 批量删除1-2-3 单个删除1
	 * 
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids")String ids) {

		if (ids.contains("-")) {
			// 组装id的集合
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");

			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);

		} else {
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}

		return Msg.success();
	}

	/**
	 * 员工删除方法
	 * 
	 * @param id
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public Msg deleteEmpById(@PathVariable("id") Integer id) {

		employeeService.deleteEmp(id);
		return Msg.success();
	}*/

	/**
	 * 员工更新方法
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "emp/{empId}", method = RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee) {

		employeeService.updateEmp(employee);

		return Msg.success();
	}

	/**
	 * 根据id查询员工
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}

	/**
	 * 检查用户名是否可用
	 * 
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUser")
	public Msg checkUser(@RequestParam(value = "empName") String empName) {
		// 先判断用户名是否是合法的表达式
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if (!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "用户名可以是2-5位中文或者6-16位英文和数字的组合");
		}
		// 数据库用户名重复校验
		boolean b = employeeService.checkUser(empName);

		if (b) {
			return Msg.success();

		} else {
			return Msg.fail().add("va_msg", "用户名已存在");
		}

	}

	/**
	 * response作用将对象转换为json对象返回 responseBody正常工作需要导入jackson的包 查询员工数据（分页查询）
	 * 
	 * @return
	 */

	/**
	 * 员工保存
	 * 
	 * @author dell
	 *
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误字段名：" + fieldError.getField());
				System.out.println("错误信息：" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		} else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}

	}

	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

		// 这不是分页
		// 引入pageHelper
		// 在查询之前只需要调用pageHelper,传入页码，以及每一页的大小
		PageHelper.startPage(pn, 5);

		List<Employee> emps = employeeService.getAll();
		// 使用pageInfo包装查询后的结果，它封装了分页的详细信息
		// 连续显示5页
		PageInfo page = new PageInfo(emps, 5);

		return Msg.success().add("pageInfo", page);

	}

	// @RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// 这不是分页
		// 引入pageHelper
		// 在查询之前只需要调用pageHelper,传入页码，以及每一页的大小
		PageHelper.startPage(pn, 5);

		List<Employee> emps = employeeService.getAll();
		// 使用pageInfo包装查询后的结果，它封装了分页的详细信息
		// 连续显示5页
		PageInfo page = new PageInfo(emps, 5);

		model.addAttribute("pageInfo", page);
		return "list";
	}

}
