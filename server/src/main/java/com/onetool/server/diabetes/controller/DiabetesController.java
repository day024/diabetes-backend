package com.onetool.server.diabetes.controller;

import com.onetool.server.diabetes.dto.DiabetesRequest;
import com.onetool.server.diabetes.dto.DiabetesResponse;
import com.onetool.server.diabetes.service.DiabetesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diabetes")
public class DiabetesController {
    @Autowired
    private DiabetesService diabetesService;

    @PostMapping("/upload")
    public ResponseEntity<String> createDiabetes(@RequestBody DiabetesRequest diabetesRequest) {
        boolean success = diabetesService.createDiabetes(diabetesRequest);
        if (success) {
            return ResponseEntity.ok("상품이 정상적으로 등록되었습니다.");
        } else {
            return ResponseEntity.status(400).body("상품 등록에 실패하였습니다."); //TODO 예외 추가하기
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiabetesResponse> getDiabetesDetails(@PathVariable Long id) {
        DiabetesResponse diabetesResponseDTO = diabetesService.findDiabetesById(id);
        return ResponseEntity.ok(diabetesResponseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateBlueprint(@RequestBody DiabetesResponse diabetesResponse) {
        try {
            boolean success = diabetesService.updateDiabetes(diabetesResponse);
            return ResponseEntity.ok(success);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDiabetes(@PathVariable Long id) {
        return ResponseEntity.ok(diabetesService.deleteDiabetes(id));
    }

}