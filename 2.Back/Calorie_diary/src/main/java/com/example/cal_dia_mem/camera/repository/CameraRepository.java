package com.example.cal_dia_mem.camera.repository;


import com.example.cal_dia_mem.camera.entity.CameraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CameraRepository extends JpaRepository<CameraEntity,Integer> {
}
