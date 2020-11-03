package com.ssafy.IMS.controller;

import com.ssafy.IMS.model.Essay;
import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.EssayRequest;
import com.ssafy.IMS.payload.UserInfoRequest;
import com.ssafy.IMS.payload.UserProfile;
import com.ssafy.IMS.service.EssayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api(value = "자소서에 관한 정보 처리")
@RequestMapping("/api/essay")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EssayController {
    @Autowired
    private EssayService essayService;

    @ApiOperation(value = "자소서 보기")
    @GetMapping("/essay/{essayId}")
    public ResponseEntity<Essay> getEssay(@PathVariable(value = "essayId") int essayId) {
        Essay essay = essayService.getEssay(essayId);
        return new ResponseEntity< >(essay, HttpStatus.OK);
    }

    @ApiOperation(value = "자소서 작성")
    @PostMapping("/makeEssay")
    public ResponseEntity<ApiResponse> registerEssay(@Valid @RequestBody EssayRequest essayRequest) {
        Essay result = essayService.addEssay(essayRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/essay/{essayId}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "자소서를 작성했습니다."));
    }

    @ApiOperation(value = "자소서 업데이트")
    @PutMapping("/update/{essayId}")
    public ResponseEntity<Essay> updateEssay(@PathVariable(value = "essayId") int essayId,@Valid @RequestBody EssayRequest essayRequest) {
        Essay result = essayService.updateEssay(essayId,essayRequest);
        return new ResponseEntity< >(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping("/{essayId}")
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam(value = "essayId") int essayId) {
        ApiResponse apiResponse = essayService.deleteEssay(essayId);
        return new ResponseEntity< >(apiResponse, HttpStatus.OK);
    }
}
