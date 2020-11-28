package com.sheva.parkinglotdemo.train;

import com.google.common.collect.Sets;
import com.sheva.parkinglotdemo.constant.Constant;
import com.sheva.parkinglotdemo.utils.FileUtil;
import com.sheva.parkinglotdemo.utils.PlateUtil;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.ml.ANN_MLP;
import org.opencv.ml.Ml;
import org.opencv.ml.TrainData;

import java.util.Set;
import java.util.Vector;


/**
 * 基于org.opencv包实现的训练
 * 图片文字识别训练
 * 训练出来的库文件，用于识别图片中的数字和字母
 * @Author Sheva
 * @Date 2020/11/28
 */
@Slf4j
public class ANNTrain {

    private ANN_MLP ann = ANN_MLP.create();

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * 默认的训练操作的根目录
     */
    private static final String DEFAULT_PATH = Constant.DEFAULT_DIR + "train/chars_recognise_ann/";

    /**
     * 训练模型保存位置
     */
    private static final String MODEL_PATH = DEFAULT_PATH + "ann.xml";



    public void train(int predictSize, int neurons){
        log.info("Ann train start...");

        // 使用push_back，行数列数不能赋初始值
        Mat samples = new Mat();
        Vector<Integer> trainingLabels = new Vector<Integer>();

        Set<String> sampleDir = Sets.newHashSet();

        //加载数字以及字母字符
        for (int i = 0; i < Constant.numCharacter; i++) {
            sampleDir.clear();
            sampleDir.add(DEFAULT_PATH + "learn/" + Constant.strCharacters[i]);
            Vector<String> files = new Vector<>();

            for (String str : sampleDir){
                FileUtil.getFiles(str, files);
            }
            for(String filename : files){
                Mat img = Imgcodecs.imread(filename, 0);
                Mat f = PlateUtil.features(img, predictSize);
                samples.push_back(f);
                //每一幅字符图片锁对应的字符类别索引下标
                trainingLabels.add(i);
            }
        }
        samples.convertTo(samples, CvType.CV_32F);
        Mat classes = Mat.zeros(trainingLabels.size(), Constant.strCharacters.length, CvType.CV_32F);

        float[] labels = new float[trainingLabels.size()];
        for (int i = 0; i < labels.length; ++i) {
            classes.put(i, trainingLabels.get(i), 1.f);
        }

        // samples.type() == CV_32F || samples.type() == CV_32S
        TrainData train_data = TrainData.create(samples, Ml.ROW_SAMPLE, classes);

        ann.clear();
        Mat layers = new Mat(1, 3, CvType.CV_32F);
        // 样本特征数 140  10*10 + 20+20
        layers.put(0, 0, samples.cols());
        // 神经元个数
        layers.put(0, 1, neurons);
        // 字符数
        layers.put(0, 2, classes.cols());

        ann.setLayerSizes(layers);
        ann.setActivationFunction(ANN_MLP.SIGMOID_SYM, 1, 1);
        ann.setTrainMethod(ANN_MLP.BACKPROP);
        TermCriteria criteria = new TermCriteria(TermCriteria.EPS + TermCriteria.MAX_ITER, 30000, 0.0001);
        ann.setTermCriteria(criteria);
        ann.setBackpropWeightScale(0.1);
        ann.setBackpropMomentumScale(0.1);
        ann.train(train_data);
        ann.save(MODEL_PATH);
    }

    public static void main(String[] args) {
        ANNTrain annTrain = new ANNTrain();
        long start = System.currentTimeMillis();
        annTrain.train(Constant.predictSize, Constant.neurons);
        long end = System.currentTimeMillis();
        long time = end - start;
        log.info("The process last:" + time + "ms");
        log.info("Ann train end...");

    }
}