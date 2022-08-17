package com.example.objclassifier.data;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import com.example.objclassifier.domain.ObjectsClassifier;

@Component(value = "tensorflowObjectsClassifier")
public class ObjectsClassifierImpl implements ObjectsClassifier {

    private static Logger logger = LoggerFactory.getLogger(ObjectsClassifierImpl.class);
    private List<String> groups;
    private byte[] model;

    ObjectsClassifierImpl(
            @Value("${processing.model}") String modelPath, @Value("${processing.groups}") String groupsPath) {
        try {
            model = Files.readAllBytes(Paths.get(modelPath));
            groups = Files.readAllLines(Paths.get(groupsPath));
        } catch (Exception e) {
            logger.error("Error during classifier initialization.", e);
        }
    }

    private static float[] executeInceptionGraph(byte[] graphDef, Tensor image) {
        try (Graph g = new Graph()) {
            g.importGraphDef(graphDef);
            try (Session session = new Session(g);
                    Tensor result = session.runner().feed("DecodeJpeg/contents", image).fetch("softmax").run().get(0)) {
                final long[] resultShape = result.shape();
                if (result.numDimensions() != 2 || resultShape[0] != 1) {
                    logger.error(String.format(
                            "Expected model to produce a [1 N] shaped tensor where N is the number of labels, instead it produced one with shape %s",
                            Arrays.toString(resultShape)));
                }
                int labels = (int) resultShape[1];
                return result.copyTo(new float[1][labels])[0];
            }
        }
    }

    @Override
    public Set<String> classify(byte[] image) {
        Set<String> result = new HashSet<>();
        try (Tensor imageTensor = Tensor.create(image)) {
            float[] probabilities = executeInceptionGraph(model, imageTensor);
            int index = getIndex(probabilities);
            result.add(groups.get(index));
        } catch (Exception e) {
            logger.error("Can't process image.", e);
        }
        return result;
    }

    private static int getIndex(float[] probabilities) {
        int best = 0;
        for (int i = 1; i < probabilities.length; ++i) {
            if (probabilities[i] > probabilities[best]) {
                best = i;
            }
        }
        return best;
    }

}
