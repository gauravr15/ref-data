package com.odin.ref_data.utility;

import com.odin.ref_data.tracing.TraceContext;

public class CorrelationIdUtil {

    public static String getCorrelationId() {
        return TraceContext.currentTraceId();
    }

    public static void setCorrelationId(String correlationId) {
        TraceContext.setTraceId(correlationId);
    }

    public static String generateCorrelationId() {
        String traceId = java.util.UUID.randomUUID().toString();
        TraceContext.setTraceId(traceId);
        return traceId;
    }

    public static void clear() {
        TraceContext.clear();
    }
}
