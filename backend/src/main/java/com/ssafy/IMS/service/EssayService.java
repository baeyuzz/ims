package com.ssafy.IMS.service;

import com.ssafy.IMS.model.Essay;
import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.*;

import java.util.List;

public interface EssayService {

    List<Essay> getEssayByUser(int id);

    Essay getEssay(int id);

    Essay addEssay(EssayRequest essayRequest);

    Essay updateEssay(EssayUpdateRequest essayUpdateRequest);

    ApiResponse deleteEssay(int essayId);

}
