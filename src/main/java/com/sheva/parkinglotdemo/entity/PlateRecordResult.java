package com.sheva.parkinglotdemo.entity;
import lombok.Getter;
import lombok.Setter;
import org.opencv.core.Rect;

/**
 * 车牌字符识别结果
 * @Author Sheva
 * @Date 2020/11/25
 */
@Getter
@Setter
public class PlateRecordResult {

    /**
     * 字符序列
     */
    private Integer sort;

    /**
     * 字符
     */
    private String chars;

    /**
     * 识别置信度
     */
    private Double confi;

    /**
     * 字符所在轮廓，最小正矩形
     */
    private Rect rect;
}
