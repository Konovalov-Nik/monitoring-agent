package jobs;

import models.LoadAvgSample;
import org.hyperic.sigar.Sigar;
import play.jobs.Every;
import play.jobs.Job;
import play.jobs.On;
import play.jobs.OnApplicationStart;

/**
 * @author Nikita Konovalov
 */

@OnApplicationStart
@Every("10s")
public class LoadAvgCollector extends Job {
    @Override
    public void doJob() throws Exception {
        Sigar sigar = new Sigar();
        double[] loadAverage = sigar.getLoadAverage();
        for (double v : loadAverage) {
            if (v < 0.0) {
                return;
            }
        }

        LoadAvgSample sample = new LoadAvgSample(loadAverage[0], loadAverage[1], loadAverage[2]);

        sample.save();
    }
}
