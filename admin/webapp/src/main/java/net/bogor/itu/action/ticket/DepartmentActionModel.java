package net.bogor.itu.action.ticket;

import net.bogor.itu.entity.ticket.Department;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class DepartmentActionModel extends DefaultActionModel {
	private Department department = new Department();
	private EntityListWrapper<Department> departments = new EntityListWrapper<Department>();

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public EntityListWrapper<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(EntityListWrapper<Department> departments) {
		this.departments = departments;
	}

}
