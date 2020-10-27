package com.ssafy.IMS.controller;

import com.ssafy.IMS.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EssayController {
    @Autowired
    private EssayService essayService;
}
