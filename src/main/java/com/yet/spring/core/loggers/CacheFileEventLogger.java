package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

//@Component
//@DefaultLogger
public class CacheFileEventLogger extends FileEventLogger {
//    @Value("${cache.size}")
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger() {
    }

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<>(cacheSize);
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

//    @PostConstruct
//    private void init() {
//        cache = new ArrayList<>(cacheSize);
//    }

//    @PreDestroy
    private void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache() {
        cache.stream()
                .forEach(event -> {
                    event.setMessage(event.getMessage() + "(from cache)");
                    super.logEvent(event);
                });
    }

//    @Value("#{fileEventLogger.name + ' with cache'}")
    @Override
    protected void setName(String name) {
        this.name = name;
    }
}
