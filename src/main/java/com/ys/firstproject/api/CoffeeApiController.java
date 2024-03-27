package com.ys.firstproject.api;

import com.ys.firstproject.dto.CoffeeForm;
import com.ys.firstproject.entity.Coffee;
import com.ys.firstproject.repository.CoffeeRepository;
import com.ys.firstproject.service.CoffeeService;
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
    @Autowired
    private CoffeeService coffeeService;

    //REST API 커피 전체 조회
    @GetMapping("/api/coffee")
    public List<Coffee> index(){

        return coffeeService.index();
    }

    //REST API 커피 선택 조회
    @GetMapping("/api/coffee/{id}")
    public Coffee show(@PathVariable Long id){

        return coffeeService.show(id);
    }

    //REST API 커피 생성
    @PostMapping("/api/coffee")
    public Coffee create(@RequestBody CoffeeForm dto){

        return coffeeService.create(dto);
    }

    //REST API 커피 수정
    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto){

        Coffee updated = coffeeService.update(id, dto);

        return updated != null ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //REST API 커피 삭제
    @DeleteMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> deletee(@PathVariable Long id){

        Coffee deleted = coffeeService.delete(id);

        return deleted != null ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
