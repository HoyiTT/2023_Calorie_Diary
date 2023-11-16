package com.example.cal_dia_mem.foodCommend.repository;

import com.example.cal_dia_mem.foodCommend.entity.FoodCommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodCommendRepository extends JpaRepository<FoodCommendEntity,Long> {
    @Query("SELECT f FROM FoodCommendEntity f WHERE f.id BETWEEN :startId AND :endId ORDER BY RAND()")
    FoodCommendEntity findRandomEntityInRange(@Param("startId") long startId, @Param("endId") long endId);
}
