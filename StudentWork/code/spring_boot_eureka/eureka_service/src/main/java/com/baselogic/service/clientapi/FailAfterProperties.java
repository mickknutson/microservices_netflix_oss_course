package com.baselogic.service.clientapi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fail-after")
public class FailAfterProperties {

    private boolean enabled = false;
    private int count = 100;
    private long failureTime = 0;
    private int requestCount = 0;

    public long getFailureTime() {
        return failureTime;
    }
    public void setFailureTime(long failureTime) {
        this.failureTime = failureTime;
    }

    public int getRequestCount() {
        return requestCount;
    }
    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

}
