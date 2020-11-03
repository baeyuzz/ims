package com.ssafy.IMS.service;

import com.ssafy.IMS.model.Essay;
import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.*;

public interface EssayService {

    Essay getEssay(int id);

    Essay addEssay(EssayRequest essayRequest);

    Essay updateEssay(int id, EssayRequest essayRequest);

    ApiResponse deleteEssay(int essayId);

}
