package controllers;

import models.MemUsageSample;
import play.mvc.Controller;
import util.SampleHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemUsageController extends Controller {

    public static void lastMemUsage(int count) {
        if (count == 0) {
            count = SampleHelper.getDefaultQueryCount();
        }

        List<MemUsageSample> all = MemUsageSample.all().fetch();
        List<MemUsageSample> samples = SampleHelper.getLastN(all, count);
        renderJSON(samples);
    }

    public static void maxMemUsage(int count) {
        if (count == 0) {
            count = SampleHelper.getDefaultQueryCount();
        }

        List<MemUsageSample> all = MemUsageSample.all().fetch();
        List<MemUsageSample> samples = SampleHelper.getLastN(all, count);

        double max = -1;

        for (MemUsageSample sample : samples) {
            max = Math.max(max, sample.usedPercent);
        }

        Map<String, Double> result = new HashMap<>();
        result.put("maxMemUsage", max);

        renderJSON(result);
    }


    public static void minMemUsage(int count) {
        if (count == 0) {
            count = SampleHelper.getDefaultQueryCount();
        }

        List<MemUsageSample> all = MemUsageSample.all().fetch();
        List<MemUsageSample> samples = SampleHelper.getLastN(all, count);

        double min = Double.MAX_VALUE;

        for (MemUsageSample sample : samples) {
            min = Math.min(min, sample.usedPercent);
        }

        Map<String, Double> result = new HashMap<>();
        result.put("minMemUsage", min);

        renderJSON(result);
    }
}