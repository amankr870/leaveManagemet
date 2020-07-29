package com.nous.test.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.nous.test.controller.EmployeeController;
import com.nous.test.dao.LeaveApplication;
import com.nous.test.dao.LeaveDao;

@Service
public class EmployeeService implements IEmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	private LeaveDao lDao;

	@Autowired
	Environment env;

	@Override
	public void approveLeave(LeaveApplication leaveApplication) {
		logger.info("In " + this.getClass().getSimpleName() + "approveLeave()");
		if (leaveApplication.getStatus().startsWith("R")) {
			logger.info("Leave is getting rejected");
			leaveApplication.setStatus(env.getProperty("leave.reject"));
		} else {
			logger.info("Leave is getting Approved");
			leaveApplication.setStatus(env.getProperty("leave.approve"));
		}
		lDao.updateLeave(leaveApplication.getStatus(), leaveApplication.getLeaveId());
	}

	@Override
	public void applyLeave(LeaveApplication leaveApplication) {
		logger.info("In " + this.getClass().getSimpleName() + "applyLeave()");
		leaveApplication.setStatus(env.getProperty("leave.apply"));
		logger.info("Leave is getting Applied");
		lDao.save(leaveApplication);
	}

}
