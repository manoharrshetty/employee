package com.emp.model;

public class Emp extends Entity {

	private EmpBasic empBasic;

	private Dept dept;

	public EmpBasic getEmpBasic() {
		return empBasic;
	}

	public void setEmpBasic(EmpBasic empBasic) {
		this.empBasic = empBasic;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

}
