package com.example.cal_dia_mem.profile.service;

import com.example.cal_dia_mem.board.entity.BoardEntity;
import com.example.cal_dia_mem.member.repository.MemberRepository;
import com.example.cal_dia_mem.profile.dto.ProfileDTO;
import com.example.cal_dia_mem.profile.entity.ProfileEntity;
import com.example.cal_dia_mem.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    @Autowired
    private final ProfileRepository profileRepository;

    public void save(ProfileDTO profileDTO) {

        ProfileEntity profileEntity = ProfileEntity.toprofileEntiy(profileDTO);
        System.out.println("weight"+profileEntity.getCurrentWeight());
        System.out.println("weight"+profileEntity.getPurposeWeight());
        System.out.println("bmi"+profileEntity.getPurposeBMI());
        profileRepository.save(profileEntity);
    }

    public ProfileDTO modifyProfile(String myEmail) {
        Optional<ProfileEntity> optionalProfileEntity = profileRepository.findByMemberEmail(myEmail);
        if (optionalProfileEntity.isPresent()) {
            return ProfileDTO.toProfileDTO(optionalProfileEntity.get());
        } else {
            return null;
        }
    }
}
