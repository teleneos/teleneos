package org.teleneos.ticket.department;


import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	@Inject
	private DepartmentRepository departmentRepository;

	@Override
	public EntityListWrapper<Department> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return departmentRepository.findAll(limit, page);
		return departmentRepository.findByKeyword(keyword, order, orderBy,
				limit, page, "OR");
	}

	@Override
	@Transactional
	public Department save(Department department) {
		if (StringUtils.isBlank(department.getId())) {
			department.setId(null);
			departmentRepository.persist(department);
		} else {
			Department dt = departmentRepository.load(department.getId());
			dt.setName(department.getName());
			department = dt;
		}
		return department;
	}

}
