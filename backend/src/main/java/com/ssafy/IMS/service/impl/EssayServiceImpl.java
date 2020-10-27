package com.ssafy.IMS.service.impl;

import com.ssafy.IMS.repository.EssayRepository;
import com.ssafy.IMS.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EssayServiceImpl implements EssayService {
    @Autowired
    private EssayRepository essayRepository;
}
