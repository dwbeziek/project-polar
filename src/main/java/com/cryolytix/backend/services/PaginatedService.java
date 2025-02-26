package com.cryolytix.backend.services;

import org.springframework.data.domain.Page;
import java.util.Map;

public interface PaginatedService<T> {
    Page<T> findAll(Map<String, String> params, int page, int size);
}
