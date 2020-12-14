package com.sheva.parkinglotdemo.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Sheva
 * @Date 2020/11/25
 */
public class Constants {

    /**
     * 使用项目的绝对路径
     */
    public static final String BASE_DIR = "/Users/sheva/IdeaProjects/parking-lot-demo/";


    public static final String DEFAULT_DIR = BASE_DIR + "PlateDetect/";
    /**
     * 处理过程与测试过程目录
     */
    public static final String DEFAULT_TEMP_DIR = BASE_DIR + "PlateDetect/temp/";
    public static final String DEFAULT_TEST_DIR = BASE_DIR + "PlateDetect/test/";


    public static final String DEFAULT_TYPE = "png,jpg,jpeg,bmp";

    public static final String DEFAULT_SVM_PATH = "model/1602828039163_svm.xml";
    public static final String DEFAULT_ANN_PATH = "model/ann.xml";
    public static final String DEFAULT_ANN_CN_PATH = "model/ann_cn.xml";
    public static final String DEFAULT_ANN_GREEN_PATH = "model/ann_green.xml";

    // 图片中，车牌图块尺寸允许的像素值范围  (width * height * multiple)
    public static final Integer DEFAULT_MIN_SIZE = 44 * 14 * 1;
    public static final Integer DEFAULT_MAX_SIZE = 44 * 14 * 80;

    // 图片中，车牌图块尺寸允许的  width/height 比例
    public static final Integer DEFAULT_MIN_RATIO =  1;
    public static final Integer DEFAULT_MAX_RATIO =  10;

    // 提取到车牌图块之后，调整图块的大小（像素值）
    /**
     * 提取到车牌土块之后，调整土块的像素值
     * DEFAULT_WIDTH 默认宽度
     * DEFAULT_HEIGHT 默认高度
     */
    public static final int DEFAULT_WIDTH = 136;
    public static final int DEFAULT_HEIGHT = 36;

    // 车牌错切调整像素值范围
    public static final Integer DEFAULT_MIN_SHEAR_PX = 8;
    public static final Integer DEFAULT_MAX_SHEAR_PX = 40;

    /**
     * 判断是否是车牌的正则表达式
     */
    public static final String plateReg = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})";

    public static final int predictSize = 10;

    public static final int neurons = 40;

    // 中国车牌; 34个字符; 没有 字母I、字母O
    public static final char strCharacters[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    // 没有I和0, 10个数字与24个英文字符之和
    public static final Integer numCharacter = strCharacters.length;

    // 并不全面，有些省份没有训练数据所以没有字符
    // 有些后面加数字2的表示在训练时常看到字符的一种变形，也作为训练数据存储
    public static final String strChinese[] = {
            "zh_cuan",  /*川*/
            "zh_e",     /*鄂*/
            "zh_gan",   /*赣*/
            "zh_gan1",  /*甘*/
            "zh_gui",   /*贵*/
            "zh_gui1",  /*桂*/
            "zh_hei",   /*黑*/
            "zh_hu",    /*沪*/
            "zh_ji",    /*冀*/
            "zh_jin",   /*津*/
            "zh_jing",  /*京*/
            "zh_jl",    /*吉*/
            "zh_liao",  /*辽*/
            "zh_lu",    /*鲁*/
            "zh_meng",  /*蒙*/
            "zh_min",   /*闽*/
            "zh_ning",  /*宁*/
            "zh_qing",  /*青*/
            "zh_qiong", /*琼*/
            "zh_shan",  /*陕*/
            "zh_su",    /*苏*/
            "zh_sx",    /*晋*/
            "zh_wan",   /*皖*/
            "zh_xiang", /*湘*/
            "zh_xin",   /*新*/
            "zh_yu",    /*豫*/
            "zh_yu1",   /*渝*/
            "zh_yue",   /*粤*/
            "zh_yun",   /*云*/
            "zh_zang",  /*藏*/
            "zh_zhe"    /*浙*/
    };

    /* 34+31=65 34个字符跟31个汉字 */
    public static final Integer numAll = strCharacters.length + strChinese.length;

    public static Map<String, String> KEY_CHINESE_MAP = new HashMap<String, String>();

    static {
        if (KEY_CHINESE_MAP.isEmpty()) {
            KEY_CHINESE_MAP.put("zh_cuan", "川");
            KEY_CHINESE_MAP.put("zh_e", "鄂");
            KEY_CHINESE_MAP.put("zh_gan", "赣");
            KEY_CHINESE_MAP.put("zh_gan1", "甘");
            KEY_CHINESE_MAP.put("zh_gui", "贵");
            KEY_CHINESE_MAP.put("zh_gui1", "桂");
            KEY_CHINESE_MAP.put("zh_hei", "黑");
            KEY_CHINESE_MAP.put("zh_hu", "沪");
            KEY_CHINESE_MAP.put("zh_ji", "冀");
            KEY_CHINESE_MAP.put("zh_jin", "津");
            KEY_CHINESE_MAP.put("zh_jing", "京");
            KEY_CHINESE_MAP.put("zh_jl", "吉");
            KEY_CHINESE_MAP.put("zh_liao", "辽");
            KEY_CHINESE_MAP.put("zh_lu", "鲁");
            KEY_CHINESE_MAP.put("zh_meng", "蒙");
            KEY_CHINESE_MAP.put("zh_min", "闽");
            KEY_CHINESE_MAP.put("zh_ning", "宁");
            KEY_CHINESE_MAP.put("zh_qing", "青");
            KEY_CHINESE_MAP.put("zh_qiong", "琼");
            KEY_CHINESE_MAP.put("zh_shan", "陕");
            KEY_CHINESE_MAP.put("zh_su", "苏");
            KEY_CHINESE_MAP.put("zh_sx", "晋");
            KEY_CHINESE_MAP.put("zh_wan", "皖");
            KEY_CHINESE_MAP.put("zh_xiang", "湘");
            KEY_CHINESE_MAP.put("zh_xin", "新");
            KEY_CHINESE_MAP.put("zh_yu", "豫");
            KEY_CHINESE_MAP.put("zh_yu1", "渝");
            KEY_CHINESE_MAP.put("zh_yue", "粤");
            KEY_CHINESE_MAP.put("zh_yun", "云");
            KEY_CHINESE_MAP.put("zh_zang", "藏");
            KEY_CHINESE_MAP.put("zh_zhe", "浙");
        }

    }

    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 系统用户授权缓存
     */
    public static final String SYS_AUTH_CACHE = "sys-authCache";

    /**
     * 参数管理 cache name
     */
    public static final String SYS_CONFIG_CACHE = "sys-config";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache name
     */
    public static final String SYS_DICT_CACHE = "sys-dict";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 加密算法名称
     */
    public static String ALGORITHM_NAME = "MD5";
    /**
     * 加密迭代次数
     */
    public static int HASH_ITERATIONS = 5;


}