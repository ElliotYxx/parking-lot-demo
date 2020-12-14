package com.sheva.parkinglotdemo.train;

import com.google.common.collect.Sets;
import com.sheva.parkinglotdemo.constant.Constants;
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
 * @Author Sheva
 * @Date 2020/11/28
 */

@Slf4j
public class CnANNTrain {
    private ANN_MLP ann = ANN_MLP.create();

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * 默认的训练操作的根目录
     */
    private static final String DEFAULT_PATH = Constants.DEFAULT_DIR + "train/chars_recognise_ann/";

    /**
     * 训练模型保存位置
     */
    private static final String MODEL_PATH = DEFAULT_PATH + "ann_cn.xml";

    public void train(int predictSize, int neurons) {
        log.info("CnAnn Train start...");
        Mat samples = new Mat(); // 使用push_back，行数列数不能赋初始值
        Vector<Integer> trainingLabels = new Vector<Integer>();

        Set<String> sampleDir = Sets.newHashSet();
        ;

        // 加载汉字字符
        for (int i = 0; i < Constants.strChinese.length; i++) {

            sampleDir.clear();
            sampleDir.add(DEFAULT_PATH + "learn/" + Constants.strChinese[i]);

            Vector<String> files = new Vector<String>();
            for (String str : sampleDir) {
                FileUtil.getFiles(str, files);
            }
            for (String filename : files) {
                Mat img = Imgcodecs.imread(filename, 0);
                // 原图样本
                samples.push_back(PlateUtil.features(img, predictSize));
                trainingLabels.add(i);
            }
        }

        samples.convertTo(samples, CvType.CV_32F);

        Mat classes = Mat.zeros(trainingLabels.size(), Constants.strChinese.length, CvType.CV_32F);

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
        CnANNTrain CnAnnTrain = new CnANNTrain();
        long start = System.currentTimeMillis();
        CnAnnTrain.train(Constants.predictSize, Constants.neurons);
        long end = System.currentTimeMillis();
        long time = end - start;
        log.info("The process last:" + time + "ms");
        log.info("CnAnn train end...");

    }

}
