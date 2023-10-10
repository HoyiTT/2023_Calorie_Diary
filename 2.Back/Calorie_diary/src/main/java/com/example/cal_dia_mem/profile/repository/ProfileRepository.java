package com.example.cal_dia_mem.profile.repository;

import com.example.cal_dia_mem.profile.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity,String> {

}

