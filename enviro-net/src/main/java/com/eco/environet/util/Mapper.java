package com.eco.environet.util;

import org.springframework.data.domain.Page;

import java.util.List;

public interface Mapper {

    <T, U> U map(T source, Class<U> targetClass, String... ignoredFields);

    <T, U> Page<U> mapPage(Page<T> sourcePage, Class<U> targetClass, String... ignoredFields);

    <T, U> List<U> mapList(List<T> sourceList, Class<U> targetClass, String... ignoredFields);
}
