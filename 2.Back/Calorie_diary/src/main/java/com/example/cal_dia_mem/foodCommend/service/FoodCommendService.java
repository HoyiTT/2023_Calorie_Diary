package com.example.cal_dia_mem.foodCommend.service;

import com.example.cal_dia_mem.diary.dto.DiaryDTO;
import com.example.cal_dia_mem.diary.entity.DiaryEntity;
import com.example.cal_dia_mem.diary.service.DiaryService;
import com.example.cal_dia_mem.foodCommend.dto.FoodCommendDTO;
import com.example.cal_dia_mem.foodCommend.entity.FoodCommendEntity;
import com.example.cal_dia_mem.foodCommend.repository.FoodCommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodCommendService {

    private final FoodCommendRepository foodCommendRepository;
    @Autowired
    private final DiaryService diaryService;

    //범위를 입력 받아 랜덤하게 추출
    public FoodCommendEntity getRandomFoodInRange(int startId, int endId) {
        Random random = new Random();
        long randomId = startId + random.nextInt(endId - startId + 1);
        return foodCommendRepository.findById(randomId).orElse(null);
    }

    //
    public List<FoodCommendDTO> commendFood() {
        List<FoodCommendDTO> foodCommendDTOList = new ArrayList<>();
        FoodCommendDTO foodCommendDTO;

        FoodCommendEntity foodCommendEntity=getRandomFoodInRange(1,5);
        foodCommendDTO=FoodCommendDTO.toFoodCommendDTO(foodCommendEntity);
        foodCommendDTOList.add(foodCommendDTO);

        foodCommendEntity=getRandomFoodInRange(6,10);
        foodCommendDTO=FoodCommendDTO.toFoodCommendDTO(foodCommendEntity);
        foodCommendDTOList.add(foodCommendDTO);

        foodCommendEntity=getRandomFoodInRange(11,15);
        foodCommendDTO=FoodCommendDTO.toFoodCommendDTO(foodCommendEntity);
        foodCommendDTOList.add(foodCommendDTO);

        return foodCommendDTOList;

    }

    public String foodCommendInfo (List<FoodCommendDTO> foodCommendDTOList){
        double carbohydrate=totalCarbohydrate(foodCommendDTOList);
        double protein = totalProtein(foodCommendDTOList);
        double fat = totalFat(foodCommendDTOList);
        double kcal =totalKcal(foodCommendDTOList);
        String info="위 식단을 모두 섭취 시 총 "+kcal+"칼로리를 섭취 하실 수 있습니다. 이 안에 포함된 영양 성분은 총"+
                +carbohydrate+"의 탄수화물, "+protein+"의 단백질, "+fat+"의 지방 입니다.";
        return info;

    }

    //탄수화물 합
    public double totalCarbohydrate(List<FoodCommendDTO> foodCommendDTOList){
        double carbohydrateValue;
        double carbohydrateSum = 0.0;

        for(FoodCommendDTO dto : foodCommendDTOList){
            try {
                System.out.println("씨발새끼"+dto.getCarbohydrate());
                carbohydrateValue = Double.parseDouble(dto.getCarbohydrate());
                System.out.println("탄수화물 합 : "+carbohydrateValue);
                carbohydrateSum+=carbohydrateValue;
            } catch (NumberFormatException e){
                System.err.println("숫자로 변환할 수 없습니다1.");
            }
        }

        return carbohydrateSum;
    }

    // 단백질 합 구하기
    public double totalProtein(List<FoodCommendDTO> foodCommendDTOList){
        double proteinValue;
        double proteinSum = 0.0;

        for(FoodCommendDTO dto : foodCommendDTOList){
            try {
                proteinValue = Double.parseDouble(dto.getProtein());
                proteinSum+=proteinValue;
            } catch (NumberFormatException e){
                System.err.println("숫자로 변환할 수 없습니다2.");
            }
        }
        return proteinSum;
    }

    //지방 합 구하기
    public double totalFat(List<FoodCommendDTO> foodCommendDTOList){
        double fatValue;
        double fatSum = 0.0;

        for(FoodCommendDTO dto : foodCommendDTOList){
            try {
                fatValue = Double.parseDouble(dto.getFat());
                fatSum+=fatValue;
            } catch (NumberFormatException e){
                System.err.println("숫자로 변환할 수 없습니다3.");
            }
        }
        return fatSum;
    }

    //칼로리 합
    public double totalKcal(List<FoodCommendDTO> foodCommendDTOList){
        double kcalValue;
        double kcalSum = 0.0;

        for(FoodCommendDTO dto : foodCommendDTOList){
            try {
                kcalValue = Double.parseDouble(dto.getFat());
                kcalSum+=kcalValue;
            } catch (NumberFormatException e){
                System.err.println("숫자로 변환할 수 없습니다3.");
            }
        }
        return kcalSum;
    }

}
