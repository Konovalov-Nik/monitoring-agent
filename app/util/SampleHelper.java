package util;

import models.Sample;
import play.Play;
import play.db.jpa.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Nikita Konovalov
 */
public class SampleHelper {
    public static<T> List<T> getLastN(List<T> all, int n) {
        Collections.sort(all, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return -((Sample)o1).getTimestamp().compareTo(((Sample)o2).getTimestamp());
            }
        });

        n = Math.min(n, all.size());

        return all.subList(0, n);
    }

    public static int getDefaultQueryCount() {
        return Integer.parseInt(Play.configuration.getProperty("defaultQueryCount"));
    }
}
