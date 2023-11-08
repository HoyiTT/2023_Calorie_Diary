package com.example.cal_dia_mem.profile.entity;

import com.example.cal_dia_mem.member.dto.MemberDTO;
import com.example.cal_dia_mem.profile.dto.ProfileDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="memberProfile")
public class ProfileEntity{
    @Id
    private String memberEmail;
    @Column(unique = true)
    @NotNull
    private String memberName;
    @Column
    private String gender;
    @Column
    private String height;
    @Column
    private String currentWeight;
    @Column
    private String purposeWeight;
    @Column
    private String purposeBMI;
    @Column
    private String muscle;
    @Column
    private String bodyFat;

    public static ProfileEntity toprofileEntiy(ProfileDTO profileDTO){
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setMemberEmail(profileDTO.getMemberEmail());
        profileEntity.setMemberName(profileDTO.getMemberName());
        profileEntity.setGender(profileDTO.getGender());
        profileEntity.setHeight(profileDTO.getHeight());
        profileEntity.setCurrentWeight(profileEntity.getCurrentWeight());
        profileEntity.setPurposeWeight(profileEntity.getPurposeWeight());
        profileEntity.setPurposeBMI(profileEntity.getPurposeBMI());
        profileEntity.setMuscle(profileEntity.getMuscle());
        profileEntity.setBodyFat(profileEntity.getBodyFat());


        return profileEntity;
    }


}
