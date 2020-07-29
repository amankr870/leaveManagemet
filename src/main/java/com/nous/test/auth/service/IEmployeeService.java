package com.nous.test.auth.service;

import com.nous.test.dao.ApprovalDTO;
import com.nous.test.dao.LeaveApplication;
import com.nous.test.dao.LeaveDTO;

public interface IEmployeeService {

	void approveLeave(ApprovalDTO leave);

	void applyLeave(LeaveDTO leaveApplication);

}
