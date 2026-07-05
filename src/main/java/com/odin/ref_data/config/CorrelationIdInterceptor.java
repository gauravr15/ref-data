package com.odin.ref_data.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.odin.ref_data.tracing.TraceContext;

@Component
public class CorrelationIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = TraceContext.resolveTraceId(request);
        TraceContext.setTraceId(traceId);
        response.setHeader(TraceContext.CORRELATION_ID_HEADER, traceId);
        response.setHeader(TraceContext.REQUEST_CHECKSUM_HEADER, traceId);
        String traceparent = TraceContext.currentTraceparent();
        if (traceparent != null) {
            response.setHeader(TraceContext.TRACEPARENT_HEADER, traceparent);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TraceContext.clear();
    }
}
