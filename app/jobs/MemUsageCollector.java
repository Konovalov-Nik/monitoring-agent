package jobs;

import models.MemUsageSample;
import org.hyperic.sigar.Sigar;
import play.jobs.Every;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

/**
 * @author Nikita Konovalov
 */

@OnApplicationStart
@Every("10s")
public class MemUsageCollector extends Job {
    @Override
    public void doJob() throws Exception {
        double usedPercent = new Sigar().getMem().getUsedPercent();

        new MemUsageSample(usedPercent).save();
    }
}
