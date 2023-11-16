package com.example.cal_dia_mem.foodCommend.dto;

import com.example.cal_dia_mem.foodCommend.entity.FoodCommendEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodCommendDTO {
    private long id;
    private String foodName;
    private String kcal;
    private String carbohydrate;
    private String protein;
    private String fat;
    private String sugars;
    private String salt;

    public static FoodCommendDTO toFoodCommendDTO(FoodCommendEntity foodCommendEntity){
        FoodCommendDTO foodCommendDTO=new FoodCommendDTO();
        foodCommendDTO.setId(foodCommendEntity.getId());
        foodCommendDTO.setFoodName(foodCommendEntity.getFoodName());
        foodCommendDTO.setKcal(foodCommendEntity.getKcal());
        foodCommendDTO.setCarbohydrate(foodCommendEntity.getCarbohydrate());
        foodCommendDTO.setProtein(foodCommendEntity.getProtein());
        foodCommendDTO.setFat(foodCommendEntity.getFat());
        foodCommendDTO.setSugars(foodCommendDTO.getSugars());
        foodCommendDTO.setSalt(foodCommendDTO.getSalt());

        return foodCommendDTO;
    }
}
