package com.ys.firstproject.service;

import com.ys.firstproject.dto.CoffeeForm;
import com.ys.firstproject.entity.Coffee;
import com.ys.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {

        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {

        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm dto) {

        // dto를 entity로 변환
        Coffee entity = dto.toEntity();
        // entity DB 저장 (create 요청 HTTP BODY 내용에 id 값이 없어야지만 생성시켜줄것 -> 생성 명령이 수정명령으로 오염 방지)
        return entity.getId() != null ? null : coffeeRepository.save(entity);
    }

    public Coffee update(Long id, CoffeeForm dto) {

        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null || target.getId() != id){

            return null;
        }
        target.patch(dto.toEntity());
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {

        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null){
            return null;
        }

        coffeeRepository.delete(target);
        return target;
    }
}
