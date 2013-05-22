package org.teleneos.ticket.department;


import org.meruvian.yama.persistence.EntityListWrapper;

public interface DepartmentService {
	EntityListWrapper<Department> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);
	Department save(Department department);
}
