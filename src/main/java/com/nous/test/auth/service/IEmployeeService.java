package com.nous.test.auth.service;

import com.nous.test.dao.LeaveApplication;

public interface IEmployeeService {

	void approveLeave(LeaveApplication leaveApplication);

	void applyLeave(LeaveApplication leaveApplication);

}
