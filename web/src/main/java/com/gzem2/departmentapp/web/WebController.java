package com.gzem2.departmentapp.web;

import com.gzem2.departmentapp.model.Employee;
import com.gzem2.departmentapp.model.Department;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {

	@Autowired
	private RestTemplate restTemplate;

	@InitBinder     
	public void initBinder(WebDataBinder binder){
    	binder.registerCustomEditor(LocalDate.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}
	
	private String apiBase = "http://localhost:8080/rest";

	@GetMapping("/")
	public String getDepartmentsIndex(Model model) {
		ResponseEntity<List<Department>> response = restTemplate.exchange(
			apiBase + "/departments",
  			HttpMethod.GET,
  			null,
  			new ParameterizedTypeReference<List<Department>>(){});
		List<Department> data = response.getBody();

		List<String> averageSalaries = new ArrayList<>();
		for(Integer i = 0; i < data.size(); i++) {
			averageSalaries.add(restTemplate.getForObject(apiBase + "/employees/average/" + data.get(i).getId().toString(), String.class));
		}

		model.addAttribute("data", data);
		model.addAttribute("salaries", averageSalaries);

		return "departments-list";
	}

	@GetMapping("/department/{id}")
	public String getEmployeesByDepartment(@PathVariable(value="id") String id, Model model) {
		ResponseEntity<List<Employee>> response = restTemplate.exchange(
			apiBase + "/employees/department/" + id,
  			HttpMethod.GET,
  			null,
  			new ParameterizedTypeReference<List<Employee>>(){});
		List<Employee> data = response.getBody();

		Department department = restTemplate.getForObject(apiBase + "/departments/" + id, Department.class);
		String departmentName = department.getDepartmentName();

		model.addAttribute("data", data);
		model.addAttribute("title", departmentName);
		model.addAttribute("departmentName", departmentName);
		model.addAttribute("departmentId", id);

		return "employees-list";
	}

	@GetMapping("/employees")
	public String getEmployees(@RequestParam(required = false) String birthday, @RequestParam(required = false) String birthdayFrom,
			@RequestParam(required = false) String birthdayTo, Model model) {

		ResponseEntity<List<Employee>> response;
		String title;

		if (birthday != null) {
			response = restTemplate.exchange(
				apiBase + "/employees/birthday/" + birthday,
  				HttpMethod.GET,
  				null,
  				new ParameterizedTypeReference<List<Employee>>(){});
			List<String> bd = Arrays.asList(birthday.split("-"));
			Collections.reverse(bd);	

			title = "результаты поиска: " + String.join("/", bd.toArray(new String[bd.size()]));
			model.addAttribute("searchDate", birthday);

		} else if(birthdayFrom != null && birthdayTo != null){
			response = restTemplate.exchange(
				apiBase + "/employees/bornbetween/" + birthdayFrom + "/" + birthdayTo,
  				HttpMethod.GET,
  				null,
  				new ParameterizedTypeReference<List<Employee>>(){});
			List<String> bdFrom = Arrays.asList(birthdayFrom.split("-"));
			Collections.reverse(bdFrom);
			List<String> bdTo = Arrays.asList(birthdayTo.split("-"));
			Collections.reverse(bdTo);
			
			title = "результаты поиска: " + String.join("/", bdFrom.toArray(new String[bdFrom.size()])) + " - " + String.join("/", bdTo.toArray(new String[bdTo.size()]));
			model.addAttribute("searchFrom", birthdayFrom);
			model.addAttribute("searchTo", birthdayTo);

		} else {
			response = restTemplate.exchange(
				apiBase + "/employees",
  				HttpMethod.GET,
  				null,
  				new ParameterizedTypeReference<List<Employee>>(){});
			title = "все отделы";
		}

		List<Employee> data = response.getBody();

		List<String> departmentList = new ArrayList<>();
		for(Integer i = 0; i < data.size(); i++) {
			Long depId = data.get(i).getDepartmentId();
			Department department = restTemplate.getForObject(apiBase + "/departments/" + depId, Department.class);
			departmentList.add(department.getDepartmentName());
		}

		model.addAttribute("data", data);
		model.addAttribute("title", title);
		model.addAttribute("departmentList", departmentList);
		return "employees-list";
	}

	@GetMapping("/employee/{id}")
	public String getEmployee(@PathVariable(value="id") String id, Model model) {
		Employee data = restTemplate.getForObject(apiBase + "/employees/" + id, Employee.class);
		String departmentName = restTemplate.getForObject(apiBase + "/departments/" + data.getDepartmentId(), Department.class).getDepartmentName();

		model.addAttribute("id", id);
		model.addAttribute("data", data);
		model.addAttribute("departmentName", departmentName);
		return "employee";
	}

	@GetMapping("/employee/edit")
	public String getEmployeeForm(@RequestParam(required = false) Long department, @RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			Employee data = restTemplate.getForObject(apiBase + "/employees/" + id, Employee.class);
			model.addAttribute("id", id);
			model.addAttribute("data", data);
		}

		if (department != null) {
			model.addAttribute("departmentId", department);
		}

		ResponseEntity<List<Department>> response = restTemplate.exchange(
			apiBase + "/departments",
  			HttpMethod.GET,
  			null,
  			new ParameterizedTypeReference<List<Department>>(){});
		List<Department> departmentList = response.getBody();

		model.addAttribute("departmentList", departmentList);

		return "employee-form";
	}

	@RequestMapping(value="/employee",
                method=RequestMethod.POST,
                consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
	public String postEmployee(Employee emp){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Employee> request = new HttpEntity<Employee>(emp, headers);
		Long id = emp.getId();

		if(id == null){
			restTemplate.postForEntity(apiBase + "/employees", request , String.class);
		} else {
			restTemplate.put(apiBase + "/employees/" + id, request);
		}

		return "redirect:/employees";
	}

	@GetMapping("/employee/delete/{id}")
	public String deleteEmployee(@PathVariable(value="id") String id, Model model) {
		restTemplate.delete(apiBase + "/employees/" + id);
		return "redirect:/employees";
	}

	@GetMapping("/department/edit")
	public String getDepartmentForm(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			Department data = restTemplate.getForObject(apiBase + "/departments/" + id, Department.class);
			model.addAttribute("id", id);
			model.addAttribute("data", data);
		}
		return "department-form";
	}

	@RequestMapping(value="/department",
                method=RequestMethod.POST,
                consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
	public String postDepartment(Department dep){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Department> request = new HttpEntity<Department>(dep, headers);
		Long id = dep.getId();

		if(id == null){
			restTemplate.postForEntity(apiBase + "/departments", request , String.class);
		} else {
			restTemplate.put(apiBase + "/departments/" + id, request);
		}

		return "redirect:/";
	}

	@GetMapping("/department/delete/{id}")
	public String deleteDepartment(@PathVariable(value="id") String id, Model model) {
		try {
			restTemplate.delete(apiBase + "/departments/" + id);
		} catch (Exception e) {
			if(e.getMessage().contains("409")) {
				model.addAttribute("message", "Отдел не пустой");
				return "error";
			} else {
				model.addAttribute("message", e.getMessage());
				return "error";
			}
		}
		return "redirect:/";
	}

}