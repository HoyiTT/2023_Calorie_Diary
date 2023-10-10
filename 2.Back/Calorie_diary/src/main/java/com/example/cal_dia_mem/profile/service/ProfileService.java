package com.example.cal_dia_mem.profile.service;

import com.example.cal_dia_mem.board.entity.BoardEntity;
import com.example.cal_dia_mem.member.repository.MemberRepository;
import com.example.cal_dia_mem.profile.dto.ProfileDTO;
import com.example.cal_dia_mem.profile.entity.ProfileEntity;
import com.example.cal_dia_mem.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void save(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = ProfileEntity.toprofileEntiy(profileDTO);
        profileRepository.save(profileEntity);
    }


 /*   public ProfileDTO modify(String memberEmail) {
        Optional<ProfileEntity> profileEntity = this.profileRepository.findById(memberEmail);
        ProfileEntity profileEntity1 = profileEntity.get();


    }*/
}
