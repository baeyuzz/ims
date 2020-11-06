package com.ssafy.IMS.service.impl;

import com.ssafy.IMS.exception.ResourceNotFoundException;
import com.ssafy.IMS.model.Essay;
import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.EssayRequest;
import com.ssafy.IMS.payload.EssayUpdateRequest;
import com.ssafy.IMS.repository.EssayRepository;
import com.ssafy.IMS.repository.UserRepository;
import com.ssafy.IMS.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EssayServiceImpl implements EssayService {
    @Autowired
    private EssayRepository essayRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Essay> getEssayByUser(int id) {
        List<Essay> essays = essayRepository
                .findAllByUser(
                        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)))
                .orElseThrow(() -> new ResourceNotFoundException("Essays", "id", id));
        return essays;

    }

    @Override
    public Essay getEssay(int id) {
        Essay curEssay = essayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Essay", "id", id));
        return curEssay;
    }

    @Override
    public Essay addEssay(EssayRequest essayRequest) {
        Essay newEssay = new Essay();
        newEssay.setContent(essayRequest.getContent());
        newEssay.setUser(userRepository.findByEmail(essayRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("유저를 찾지 못헀습니다"))));
        newEssay.setResult1(essayRequest.getResult1());
        newEssay.setResult2(essayRequest.getResult2());
        newEssay.setResult3(essayRequest.getResult3());
        newEssay.setResult4(essayRequest.getResult4());
        newEssay.setResult5(essayRequest.getResult5());
        newEssay.setResult6(essayRequest.getResult6());
        newEssay.setResult7(essayRequest.getResult7());
        newEssay.setResult8(essayRequest.getResult8());
        return essayRepository.save(newEssay);
    }

    @Override
    public Essay updateEssay(EssayUpdateRequest essayUpdateRequest) {
        Optional<Essay> oldEssay = essayRepository.findById(essayUpdateRequest.getId());
        oldEssay.ifPresent(newEssay -> {
            newEssay.setContent(essayUpdateRequest.getContent());
            newEssay.setResult1(essayUpdateRequest.getResult1());
            newEssay.setResult2(essayUpdateRequest.getResult2());
            newEssay.setResult3(essayUpdateRequest.getResult3());
            newEssay.setResult4(essayUpdateRequest.getResult4());
            newEssay.setResult5(essayUpdateRequest.getResult5());
            newEssay.setResult6(essayUpdateRequest.getResult6());
            newEssay.setResult7(essayUpdateRequest.getResult7());
            newEssay.setResult8(essayUpdateRequest.getResult8());
            essayRepository.save(newEssay);
        });
        return essayRepository.findById(essayUpdateRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Essay", "id", essayUpdateRequest.getId()));
    }

    @Override
    public ApiResponse deleteEssay(int essayId) {
        Essay essay = essayRepository.findById(essayId)
                .orElseThrow(() -> new ResourceNotFoundException("Essay", "id", essayId));
        essayRepository.deleteById(essayId);
        return new ApiResponse(Boolean.TRUE, "자소서가 삭제되었습니다..");
    }

    @Override
    public ApiResponse shareEssay(int essayId) {
        Optional<Essay> oldEssay = essayRepository.findById(essayId);
        oldEssay.ifPresent(newEssay -> {
            newEssay.setShare("share");
            essayRepository.save(newEssay);
        });
        return new ApiResponse(Boolean.TRUE, "자소서가 공유되었습니다..");
    }

    @Override
    public List<Essay> getSharedEssay() {
        List<Essay> essays = essayRepository.findAllByShare("share");
        return essays;

    }
}
