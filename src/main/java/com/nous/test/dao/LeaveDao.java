package com.nous.test.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LeaveDao extends CrudRepository<LeaveApplication, Integer> {

	LeaveApplication findByStatus(String status);
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE leave_application l set l.status=(:status) where l.leave_id=(:leaveid)", nativeQuery = true)
	@Transactional
	void updateLeave(@Param("status") String status, @Param("leaveid") int leaveid);
}
