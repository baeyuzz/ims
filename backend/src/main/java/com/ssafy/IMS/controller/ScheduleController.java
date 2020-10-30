package com.ssafy.IMS.controller;

import com.ssafy.IMS.service.ScheduleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "자소서에 관한 정보 처리")
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
}
