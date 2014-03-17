package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author Nikita Konovalov
 */

@Entity
public class MemUsageSample extends Model implements Sample {
    public Double usedPercent;
    public Long timestamp;

    public MemUsageSample(Double usedPercent) {
        this.usedPercent = usedPercent;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }
}
