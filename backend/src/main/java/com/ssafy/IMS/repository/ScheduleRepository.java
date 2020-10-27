package com.ssafy.IMS.repository;

import com.ssafy.IMS.model.Schedule;
import com.ssafy.IMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
