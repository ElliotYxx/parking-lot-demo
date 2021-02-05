package com.sheva.parkinglotdemo.domain.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author Sheva
 * @Date 2020/12/1
 * 停车记录表
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class ParkingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String plateNumber;
    @Column
    private Timestamp parkTime;
    @Column
    private Timestamp leftTime;
    @Column
    private float cost;
}
