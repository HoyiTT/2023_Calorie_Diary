package com.example.cal_dia_mem.diary.entity;

import com.example.cal_dia_mem.diary.dto.DiaryDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name="food_diary")
public class DiaryEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String memberEmail;
    @Column
    @CreationTimestamp
    private Timestamp createDate;
    @Column
    private String food_name;
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
    @Column
    private String cholesterol;
    @Column
    private String saturated_fatty;
    @Column
    private String transfat;

    public static DiaryEntity toDiaryEntity(DiaryDTO diaryDTO){
        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setMemberEmail(diaryDTO.getMemberEmail());
        diaryEntity.setCreateDate(diaryDTO.getCreateDate());
        diaryEntity.setFood_name(diaryDTO.getFood_name());
        diaryEntity.setKcal(diaryDTO.getKcal());
        diaryEntity.setCarbohydrate(diaryDTO.getCarbohydrate());
        diaryEntity.setProtein(diaryDTO.getProtein());
        diaryEntity.setFat(diaryDTO.getFat());
        diaryEntity.setSugars(diaryDTO.getSugars());
        diaryEntity.setSalt(diaryDTO.getSalt());
        diaryEntity.setCholesterol(diaryDTO.getCholesterol());
        diaryEntity.setSaturated_fatty(diaryDTO.getSaturated_fatty());
        diaryEntity.setTransfat(diaryDTO.getTransfat());

        return diaryEntity;
    }
}
