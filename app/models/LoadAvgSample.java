package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author Nikita Konovalov
 */

@Entity
public class LoadAvgSample extends Model implements Sample{
    public Double oneMinute;
    public Double fiveMinutes;
    public Double fifteenMinutes;
    public Long timestamp;

    public LoadAvgSample(Double oneMinute, Double fiveMinutes, Double fifteenMinutes) {
        this.oneMinute = oneMinute;
        this.fiveMinutes = fiveMinutes;
        this.fifteenMinutes = fifteenMinutes;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }


}
