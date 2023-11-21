package com.example.cal_dia_mem.foodCommend.entity;

<<<<<<< HEAD
import com.example.cal_dia_mem.diary.dto.DiaryDTO;
import com.example.cal_dia_mem.diary.entity.DiaryEntity;
=======
>>>>>>> 55631393740fd99be0357e9fd4fa185a2c769ee6
import com.example.cal_dia_mem.foodCommend.dto.FoodCommendDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="foodCommend")
public class FoodCommendEntity {
    @Id
    private long id;
    @Column
    private String foodName;
    @Column
    private String kcal;
    @Column
    private String carbohydrate;
    @Column
    private String protein;
    @Column
    private String fat;
    @Column
    private String sugars;
    @Column
    private String salt;
<<<<<<< HEAD
    @Column
    private String meal;
=======
>>>>>>> 55631393740fd99be0357e9fd4fa185a2c769ee6

    public static FoodCommendEntity toFoodCommendEntity(FoodCommendDTO foodCommendDTO){
        FoodCommendEntity foodCommendEntity=new FoodCommendEntity();
        foodCommendEntity.setId(foodCommendDTO.getId());
        foodCommendEntity.setFoodName(foodCommendDTO.getFoodName());
        foodCommendEntity.setKcal(foodCommendDTO.getKcal());
        foodCommendEntity.setCarbohydrate(foodCommendDTO.getCarbohydrate());
        foodCommendEntity.setProtein(foodCommendDTO.getProtein());
        foodCommendEntity.setFat(foodCommendDTO.getFat());
        foodCommendEntity.setSugars(foodCommendDTO.getSugars());
        foodCommendEntity.setSalt(foodCommendDTO.getSalt());
<<<<<<< HEAD
        foodCommendEntity.setMeal(foodCommendDTO.getMeal());

        return foodCommendEntity;
    }

    public static FoodCommendDTO entityToDto(FoodCommendEntity entity){
        FoodCommendDTO dto = new FoodCommendDTO();
        dto.setId(entity.getId());
        dto.setFoodName(entity.getFoodName());
        dto.setKcal(entity.getKcal());
        dto.setCarbohydrate(entity.getCarbohydrate());
        dto.setProtein(entity.getProtein());
        dto.setFat(entity.getFat());
        dto.setSugars(entity.getSugars());
        dto.setSalt(entity.getSalt());
        dto.setMeal(entity.getMeal());
        return dto;
    }
=======

        return foodCommendEntity;
    }
>>>>>>> 55631393740fd99be0357e9fd4fa185a2c769ee6
}
