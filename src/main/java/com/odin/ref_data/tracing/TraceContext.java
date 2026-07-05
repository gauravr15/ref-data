package com.odin.ref_data.tracing;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

public final class TraceContext {

    public static final String TRACE_ID_MDC_KEY = "traceId";
    public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    public static final String REQUEST_CHECKSUM_HEADER = "requestChecksum";
    public static final String TRACEPARENT_HEADER = "traceparent";

    private TraceContext() {
    }

    public static String resolveTraceId(HttpServletRequest request) {
        String traceId = firstNonBlank(
                request.getHeader(CORRELATION_ID_HEADER),
                request.getHeader(REQUEST_CHECKSUM_HEADER),
                extractTraceIdFromTraceparent(request.getHeader(TRACEPARENT_HEADER)));
        return StringUtils.hasText(traceId) ? traceId.trim() : UUID.randomUUID().toString();
    }

    public static void setTraceId(String traceId) {
        if (!StringUtils.hasText(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        String normalized = traceId.trim();
        MDC.put(TRACE_ID_MDC_KEY, normalized);
        MDC.put(CORRELATION_ID_HEADER, normalized);
        MDC.put(REQUEST_CHECKSUM_HEADER, normalized);
        MDC.put("correlationId", normalized);
    }

    public static String currentTraceId() {
        return firstNonBlank(
                MDC.get(TRACE_ID_MDC_KEY),
                MDC.get(CORRELATION_ID_HEADER),
                MDC.get(REQUEST_CHECKSUM_HEADER),
                MDC.get("correlationId"));
    }

    public static String currentTraceparent() {
        String traceId = currentTraceId();
        if (!StringUtils.hasText(traceId)) {
            return null;
        }
        return "00-" + toTraceparentTraceId(traceId) + "-" + randomSpanId() + "-01";
    }

    public static void writeToHttpHeaders(HttpHeaders headers) {
        String traceId = currentTraceId();
        if (!StringUtils.hasText(traceId)) {
            return;
        }
        headers.set(CORRELATION_ID_HEADER, traceId);
        headers.set(REQUEST_CHECKSUM_HEADER, traceId);
        String traceparent = currentTraceparent();
        if (StringUtils.hasText(traceparent)) {
            headers.set(TRACEPARENT_HEADER, traceparent);
        }
    }

    public static void clear() {
        MDC.remove(TRACE_ID_MDC_KEY);
        MDC.remove(CORRELATION_ID_HEADER);
        MDC.remove(REQUEST_CHECKSUM_HEADER);
        MDC.remove("correlationId");
    }

    private static String extractTraceIdFromTraceparent(String traceparent) {
        if (!StringUtils.hasText(traceparent)) {
            return null;
        }
        String[] parts = traceparent.trim().split("-");
        if (parts.length >= 2 && parts[1].matches("[0-9a-fA-F]{32}")) {
            return parts[1].toLowerCase();
        }
        return null;
    }

    private static String toTraceparentTraceId(String traceId) {
        String hex = traceId.replaceAll("[^0-9a-fA-F]", "").toLowerCase();
        if (hex.length() >= 32) {
            return hex.substring(0, 32);
        }
        return String.format("%1$-32s", hex).replace(' ', '0');
    }

    private static String randomSpanId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (StringUtils.hasText(value)) {
                return value.trim();
            }
        }
        return null;
    }
}
