package net.bogor.itu.service.ticket;

import net.bogor.itu.entity.ticket.Department;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface DepartmentService {
	EntityListWrapper<Department> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);
	Department save(Department department);
}
