package com.baselogic.hello.filters.post;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ElapsedTimeLoggerFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(ElapsedTimeLoggerFilter.class);

    // add type, order and shouldFilter methods here

    // add run method here
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        // get the startTime stored in the context
        long startTime = (long) ctx.get("TIME");
        long currentTime = new Date().getTime();

        // calculate and log the elapsed time
        log.info("elapsed time: " + (currentTime - startTime) + "ms");
        return null;
    }
}