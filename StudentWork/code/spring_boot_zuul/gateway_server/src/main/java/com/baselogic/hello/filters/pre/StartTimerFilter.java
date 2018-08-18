package com.baselogic.hello.filters.pre;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class StartTimerFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(StartTimerFilter.class);

    // add type, order and shouldFilter methods here

    // add run method here
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        // set the current time in ms in the context
        ctx.set("TIME", new Date().getTime());
        return null;
    }

}