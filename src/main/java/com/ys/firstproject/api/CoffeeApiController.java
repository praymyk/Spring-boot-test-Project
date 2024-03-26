package com.ys.firstproject.api;

import com.ys.firstproject.dto.CoffeeForm;
import com.ys.firstproject.entity.Coffee;
import com.ys.firstproject.repository.CoffeeRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    CoffeeRepository coffeeRepository;

    //REST API 커피 전체 조회
    @GetMapping("/api/coffee")
    public List<Coffee> index(){

        return coffeeRepository.findAll();
    }

    //REST API 커피 선택 조회
    @GetMapping("/api/coffee/{id}")
    public Coffee show(@PathVariable Long id){

        return coffeeRepository.findById(id).orElse(null);
    }

    //REST API 커피 생성
    @PostMapping("/api/coffee")
    public Coffee create(@RequestBody CoffeeForm dto){

        Coffee entity = dto.toEntity();
        log.info("entity :{}", entity);

        return coffeeRepository.save(entity);
    }

    //REST API 커피 수정
    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto){

        Coffee entity = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        log.info("수정요청정보 : {}, 수정대상정보 : {}", entity, target);

        if(target == null || target.getId() != id){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

       target.patch(entity);

        return ResponseEntity.status(HttpStatus.OK).body(coffeeRepository.save(target));
    }

    //REST API 커피 삭제
    @DeleteMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> deletee(@PathVariable Long id){

        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            coffeeRepository.delete(target);
            log.info("삭제된 커피 : {}", target);
            return ResponseEntity.status(HttpStatus.OK).body(target);
        }
    }
}
