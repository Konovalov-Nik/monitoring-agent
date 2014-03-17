package jobs;

import models.LoadAvgSample;
import models.MemUsageSample;
import play.jobs.Every;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import java.util.List;

/**
 * @author Nikita Konovalov
 */

@OnApplicationStart
@Every("2h")
public class OldSampleCleaner extends Job {
    @Override
    public void doJob() throws Exception {
        long current = System.currentTimeMillis();
        // Current - 2 hours
        long marker = current - 2 * 60 * 60 * 1000;

        cleanLoadAvg(marker);
        cleanMemUsage(marker);
    }

    private void cleanLoadAvg(long marker) {

        List<LoadAvgSample> samples = LoadAvgSample.all().fetch();
        for (LoadAvgSample sample : samples) {
            if (sample.timestamp < marker) {
                sample.delete();
            }
        }
    }

    private void cleanMemUsage(long marker) {

        List<MemUsageSample> samples = MemUsageSample.all().fetch();
        for (MemUsageSample sample : samples) {
            if (sample.timestamp < marker) {
                sample.delete();
            }
        }
    }
}
