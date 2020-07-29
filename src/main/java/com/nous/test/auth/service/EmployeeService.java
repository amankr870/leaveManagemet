package com.nous.test.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.nous.test.controller.EmployeeController;
import com.nous.test.dao.ApprovalDTO;
import com.nous.test.dao.LeaveApplication;
import com.nous.test.dao.LeaveDTO;
import com.nous.test.dao.LeaveDao;

@Service
public class EmployeeService implements IEmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	private LeaveDao lDao;

	@Autowired
	Environment env;

	@Override
	public void approveLeave(ApprovalDTO leaveApplication) {
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
	public void applyLeave(LeaveDTO lDto) {
		logger.info("In " + this.getClass().getSimpleName() + "applyLeave()");
		lDto.setStatus(env.getProperty("leave.apply"));
		logger.info("Leave is getting Applied");
		
		LeaveApplication leaveApplication = new LeaveApplication();
		leaveApplication.setFromDate(lDto.getFromDate());
		leaveApplication.setToDate(lDto.getToDate());
		leaveApplication.setNotes(lDto.getNotes());
		leaveApplication.setToDate(lDto.getToDate());
		leaveApplication.setStatus(lDto.getStatus());
		
		lDao.save(leaveApplication);
	}

}
