package com.example.cal_dia_mem.diary.service;
import com.example.cal_dia_mem.api.dto.ApiDTO;
import com.example.cal_dia_mem.diary.dto.DiaryDTO;
import com.example.cal_dia_mem.diary.entity.DiaryEntity;
import com.example.cal_dia_mem.diary.repository.DiaryRepository;
import com.example.cal_dia_mem.profile.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    public void save(DiaryDTO diaryDTO) {
        DiaryEntity diaryEntity = DiaryEntity.toDiaryEntity(diaryDTO);
        diaryRepository.save(diaryEntity);
    }

    public List<DiaryDTO> callDiary(Date createDate, String myEmail){
        List<DiaryEntity> diaryEntityList =diaryRepository.findByCreateDateAndMemberEmail(createDate,myEmail);
        List<DiaryDTO> diaryDtoList = diaryEntityList.stream()
                .map(DiaryEntity::entityToDto)
                    .collect(Collectors.toList());
        return diaryDtoList;
    }
}
