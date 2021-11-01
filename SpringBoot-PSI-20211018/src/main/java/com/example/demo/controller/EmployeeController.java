package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	// 問號後代參數 不一定要有required = false
	@RequestMapping("/")
	public String index(Model model, @RequestParam(name = "department_id", required = false) Long department_id) {
		Employee employee = new Employee();
		Department department = null;
		if (department_id != null) {
			department = departmentRepository.findById(department_id).get();
			employee.setDepartment(department); // 設定員工進部門
		}
		model.addAttribute("employee", employee);
		if (department == null) { // 如果@RequestParam =null搜全部
			model.addAttribute("employees", employeeRepository.findAll());
		} else { // 全找對應部門的員工
			model.addAttribute("employees", employeeRepository.findByDepartment(department));
		}
		// 從員工對應部門全部找出
		model.addAttribute("departments", departmentRepository.findAll());
		return "employee";
	}

	@RequestMapping("/create")
	public String create(Employee employee) {
		Department department = departmentRepository.findById(employee.getDepartment().getId()).get();
		// 建立employee(多方管理)與department關聯
		employee.setDepartment(department);
		employeeRepository.save(employee);
		return "redirect:/employee/?department_id=" + department.getId();
	}

	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Employee employee = employeeRepository.findById(id).get();
		model.addAttribute("employee", employee);
		// 給下拉選單使用
		model.addAttribute("departments", departmentRepository.findAll());
		return "employee-update";
	}

	@RequestMapping("/update/{id}")
	public String update(Employee employee, @PathVariable("id") Long id) {
		Department department = departmentRepository.findById(employee.getDepartment().getId()).get();
		// 建立employee(多方管理)與department關聯
		employee.setDepartment(department);
		employeeRepository.save(employee);
		return "redirect:/employee/?department_id=" + department.getId();
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Employee employee =employeeRepository.findById(id).get();
        Long department_id = employee.getDepartment().getId();
        employeeRepository.deleteById(id);
        return "redirect:/employee/?department_id=" + department_id;
	}
	

}
