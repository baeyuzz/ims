package com.ssafy.IMS.service.impl;

import com.ssafy.IMS.repository.ScheduleRepository;
import com.ssafy.IMS.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
}
