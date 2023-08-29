package com.example.cal_dia_mem.member.dto;

import com.example.cal_dia_mem.member.entity.SiteUserEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    private String memberNickname;

    public static MemberDTO tomemberDTO(SiteUserEntity siteUserEntity){
         MemberDTO memberDTO =new MemberDTO();
         memberDTO.setId(siteUserEntity.getId());
         memberDTO.setMemberEmail(siteUserEntity.getMemberEmail());
         memberDTO.setMemberPassword(siteUserEntity.getMemberPassword());
         memberDTO.setMemberName(siteUserEntity.getMemberName());
         memberDTO.setMemberNickname(siteUserEntity.getMemberNickname());
         return memberDTO;
    }
}
