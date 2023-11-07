package com.example.cal_dia_mem.profile.dto;

import com.example.cal_dia_mem.profile.entity.ProfileEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDTO {

    private Long id;
    private String memberEmail;
    private String memberName;
    private String gender;
    private String height;
    private String currentWeight;
    private String purposeWeight;
    private String purposeBMI;
    private String muscle;
    private String bodyFat;


    public static ProfileDTO toProfileDTO(ProfileEntity profileEntity){
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profileEntity.getId());
        profileDTO.setMemberEmail(profileEntity.getMemberEmail());
        profileDTO.setMemberName(profileEntity.getMemberName());
        profileDTO.setGender(profileEntity.getGender());
        profileDTO.setHeight(profileEntity.getHeight());
        profileDTO.setCurrentWeight(profileEntity.getCurrentWeight());
        profileDTO.setPurposeWeight(profileEntity.getPurposeWeight());
        profileDTO.setPurposeBMI(profileEntity.getPurposeBMI());
        profileDTO.setMuscle(profileEntity.getMuscle());
        profileDTO.setBodyFat(profileEntity.getBodyFat());


        return profileDTO;

    }
}
