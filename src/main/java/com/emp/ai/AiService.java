package com.emp.ai;

public interface AiService<T> {
    T getResponse(String request,Class<T> type);
}
