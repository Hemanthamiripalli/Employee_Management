package com.employee.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.dtos.EmployeeDto;
import com.employee.models.Employees;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String saveEmp(EmployeeDto dto) {

		try {

			boolean exists = empRepo.existsByEmpEmail(dto.getEmpEmail());

			if (exists) {

				return "Email Already Exists";
			}

			Employees emp = new Employees();

			emp.setEmpName(dto.getEmpName());

			emp.setEmpEmail(dto.getEmpEmail());

			emp.setJobTitle(dto.getJobTitle());

			emp.setSalary(dto.getSalary());

			emp.setStatus(dto.getStatus());

			empRepo.save(emp);

			return "Success";

		} catch (Exception e) {

			e.printStackTrace();

			return "Failed";
		}
	}

	@Override
	public List<EmployeeDto> getAll() {

		List<EmployeeDto> list = new ArrayList<>();

		String query = "SELECT * FROM employees where status = 'ACTIVE'";
		jdbcTemplate.query(query, (rs) -> {
			EmployeeDto dto = new EmployeeDto();
			dto.setId(rs.getLong("id"));
			dto.setEmpName(rs.getString("emp_name"));
			dto.setEmpEmail(rs.getString("emp_email"));
			dto.setJobTitle(rs.getString("jobtitle"));
			dto.setSalary(rs.getString("salary"));
			dto.setStatus(rs.getString("status"));
			list.add(dto);
		});

			return list;
	}

	@Override
	public EmployeeDto getbyId(Long id) {

		try {

			Optional<Employees> emp = empRepo.findById(id);

			if (emp.isPresent()) {

				EmployeeDto dto = new EmployeeDto();

				dto.setId(emp.get().getId());

				dto.setEmpName(emp.get().getEmpName());

				dto.setEmpEmail(emp.get().getEmpEmail());

				dto.setJobTitle(emp.get().getJobTitle());

				dto.setSalary(emp.get().getSalary());

				dto.setStatus(emp.get().getStatus());

				return dto;
			}

			return null;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
	}

	@Override
	public String deletebyId(Long id) {

		try {

			empRepo.deleteById(id);

			return "Success";

		} catch (Exception e) {

			e.printStackTrace();

			return "Failed";
		}
	}

	@Override
	public String updateById(EmployeeDto dto, Long id) {

		try {

			Optional<Employees> emp = empRepo.findById(id);

			if (emp.isPresent()) {

				emp.get().setEmpName(dto.getEmpName());

				emp.get().setEmpEmail(dto.getEmpEmail());

				emp.get().setJobTitle(dto.getJobTitle());

				emp.get().setSalary(dto.getSalary());

				emp.get().setStatus(dto.getStatus());

				empRepo.save(emp.get());

				return "Success";
			}

			return "Employee Not Found";

		} catch (Exception e) {

			e.printStackTrace();

			return "Failed";
		}
	}

	@Override
	public EmployeeDto getByEmail(String email) {

		try {

			Optional<Employees> emp = empRepo.findByEmpEmail(email);

			if (emp.isPresent()) {

				EmployeeDto dto = new EmployeeDto();

				dto.setId(emp.get().getId());

				dto.setEmpName(emp.get().getEmpName());

				dto.setEmpEmail(emp.get().getEmpEmail());

				dto.setJobTitle(emp.get().getJobTitle());

				dto.setSalary(emp.get().getSalary());

				dto.setStatus(emp.get().getStatus());

				return dto;
			}

			return null;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
	}

	@Override
	public EmployeeDto getByName(String name) {

		try {

			Optional<Employees> emp = empRepo.findByEmpName(name);

			if (emp.isPresent()) {

				EmployeeDto dto = new EmployeeDto();

				dto.setId(emp.get().getId());

				dto.setEmpName(emp.get().getEmpName());

				dto.setEmpEmail(emp.get().getEmpEmail());

				dto.setJobTitle(emp.get().getJobTitle());

				dto.setSalary(emp.get().getSalary());

				dto.setStatus(emp.get().getStatus());

				return dto;
			}

			return null;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
	}
}