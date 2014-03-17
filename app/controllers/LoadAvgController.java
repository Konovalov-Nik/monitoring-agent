package controllers;

import play.mvc.*;

import java.util.*;

import models.*;
import util.SampleHelper;

public class LoadAvgController extends Controller {

    public static void lastLoadAvg(int count) {
        if (count == 0) {
            count = SampleHelper.getDefaultQueryCount();
        }

        List<LoadAvgSample> all = LoadAvgSample.all().fetch();
        List<LoadAvgSample> samples = SampleHelper.getLastN(all, count);
        renderJSON(samples);
    }

    public static void maxLoadAvg(int count) {
        if (count == 0) {
            count = SampleHelper.getDefaultQueryCount();
        }

        List<LoadAvgSample> all = LoadAvgSample.all().fetch();
        List<LoadAvgSample> samples = SampleHelper.getLastN(all, count);

        double m1 = -1;
        double m5 = -1;
        double m15 = -1;

        for (LoadAvgSample sample : samples) {
            m1 = Math.max(m1, sample.oneMinute);
            m5 = Math.max(m5, sample.fiveMinutes);
            m15 = Math.max(m15, sample.fifteenMinutes);
        }

        Map<String, Double> result = new HashMap<>();
        result.put("maxOneMinute", m1);
        result.put("maxFiveMinutes", m5);
        result.put("maxFifteenMinutes", m15);

        renderJSON(result);
    }


    public static void minLoadAvg(int count) {
        if (count == 0) {
            count = SampleHelper.getDefaultQueryCount();
        }

        List<LoadAvgSample> all = LoadAvgSample.all().fetch();
        List<LoadAvgSample> samples = SampleHelper.getLastN(all, count);

        double m1 = Double.MAX_VALUE;
        double m5 = Double.MAX_VALUE;
        double m15 = Double.MAX_VALUE;

        for (LoadAvgSample sample : samples) {
            m1 = Math.min(m1, sample.oneMinute);
            m5 = Math.min(m5, sample.fiveMinutes);
            m15 = Math.min(m15, sample.fifteenMinutes);
        }

        Map<String, Double> result = new HashMap<>();
        result.put("minOneMinute", m1);
        result.put("minFiveMinutes", m5);
        result.put("minFifteenMinutes", m15);

        renderJSON(result);
    }
}