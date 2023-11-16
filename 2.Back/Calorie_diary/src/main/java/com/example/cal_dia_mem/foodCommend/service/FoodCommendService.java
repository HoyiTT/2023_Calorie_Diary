package com.example.cal_dia_mem.foodCommend.service;

import com.example.cal_dia_mem.foodCommend.dto.FoodCommendDTO;
import com.example.cal_dia_mem.foodCommend.entity.FoodCommendEntity;
import com.example.cal_dia_mem.foodCommend.repository.FoodCommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCommendService {
    private final FoodCommendRepository foodCommendRepository;

    public FoodCommendEntity getRandomEntity1to5() {
        return foodCommendRepository.findRandomEntityInRange(1, 5);
    }

    public FoodCommendEntity getRandomEntity6to10() {
        return foodCommendRepository.findRandomEntityInRange(6, 10);
    }

    public FoodCommendEntity getRandomEntity11to15() {
        return foodCommendRepository.findRandomEntityInRange(11, 15);
    }

    public List<FoodCommendDTO> commendFood() {
        List<FoodCommendEntity> foodCommendEntityList = null;
        foodCommendEntityList.add(getRandomEntity1to5());
        foodCommendEntityList.add(getRandomEntity6to10());
        foodCommendEntityList.add(getRandomEntity11to15());

        System.out.println(foodCommendEntityList);
        return null;

    }
}
