package com.sheva.parkinglotdemo.repository;

import com.sheva.parkinglotdemo.domain.entity.ParkingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Sheva
 * @Date 2020/12/1
 */
public interface ParkRepository extends JpaRepository<ParkingRecord, Long> {
}
