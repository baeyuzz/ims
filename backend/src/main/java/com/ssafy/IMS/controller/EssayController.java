package com.ssafy.IMS.controller;

import com.ssafy.IMS.model.Essay;
import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.*;
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
import java.util.List;

@Api(value = "자소서에 관한 정보 처리")
@RequestMapping("/api/essay")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EssayController {
    @Autowired
    private EssayService essayService;

    @ApiOperation(value = "자소서 보기")
    @GetMapping("/getEssay/{essayId}")
    public ResponseEntity<Essay> getEssay(@PathVariable(value = "essayId") int essayId) {
        Essay essay = essayService.getEssay(essayId);
        return new ResponseEntity<>(essay, HttpStatus.OK);
    }

    @ApiOperation(value = "유저당 자소서 보기")
    @GetMapping("/getEssayByUser/{userId}")
    public ResponseEntity<List<Essay>> getEssayByUserId(@PathVariable(value = "userId") int userId) {
        List<Essay> essays = essayService.getEssayByUser(userId);
        return new ResponseEntity<>(essays, HttpStatus.OK);
    }

    @ApiOperation(value = "자소서 작성")
    @PostMapping("/makeEssay")
    public ResponseEntity<ApiResponse> registerEssay(@Valid @RequestBody EssayRequest essayRequest) {
        Essay result = essayService.addEssay(essayRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/essay/{essayId}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, Integer.toString(result.getId())));
    }

    @ApiOperation(value = "자소서 업데이트")
    @PutMapping("/update")
    public ResponseEntity<Essay> updateEssay(@Valid @RequestBody EssayUpdateRequest essayUpdateRequest) {
        Essay result = essayService.updateEssay(essayUpdateRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "자소서 삭제")
    @DeleteMapping("/{essayId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "essayId") int essayId) {
        ApiResponse apiResponse = essayService.deleteEssay(essayId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "자소서 공유")
    @PostMapping("share/{essayId}")
    public ResponseEntity<ApiResponse> shareEssay(@PathVariable(value = "essayId") int essayId) {
        ApiResponse apiResponse = essayService.shareEssay(essayId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "공유된 자소서 리스트")
    @GetMapping("/share")
    public ResponseEntity<List<Essay>> getSharedEssay() {
        List<Essay> essays = essayService.getSharedEssay();
        return new ResponseEntity<>(essays, HttpStatus.OK);
    }
}
