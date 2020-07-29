package com.nous.test.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.nous.test.dao.LeaveApplication;
import com.nous.test.dao.LeaveDao;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private LeaveDao lDao;

	@Autowired
	Environment env;

	@Override
	public void approveLeave(LeaveApplication leaveApplication) {

		if (leaveApplication.getStatus().contains("Reject")) {
			leaveApplication.setStatus(env.getProperty("leave.reject"));
		} else {
			leaveApplication.setStatus(env.getProperty("leave.approve"));
		}
		lDao.updateLeave(leaveApplication.getStatus(), leaveApplication.getLeaveId());
	}

	@Override
	public void applyLeave(LeaveApplication leaveApplication) {
		leaveApplication.setStatus(env.getProperty("leave.apply"));
		lDao.save(leaveApplication);
	}

}
