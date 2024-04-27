package com.eco.environet.util;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper {

    ModelMapper modelMapper = new ModelMapper();

    static <T, U> U map(T source, Class<U> targetClass, String... ignoredFields) {
        U mappedObject = modelMapper.map(source, targetClass);
        ignoreFields(mappedObject, List.of(ignoredFields));
        return mappedObject;
    }

    static <T, U> List<U> mapList(List<T> sourceList, Class<U> targetClass, String... ignoredFields) {
        return sourceList.stream()
                .map(source -> map(source, targetClass, ignoredFields))
                .collect(Collectors.toList());
    }

    static <T, U> Page<U> mapPage(Page<T> sourcePage, Class<U> targetClass, String... ignoredFields) {
        List<U> content = mapList(sourcePage.getContent(), targetClass, ignoredFields);
        return new PageImpl<>(content, sourcePage.getPageable(), sourcePage.getTotalElements());
    }

    static private void ignoreFields(Object object, List<String> ignoredFields) {
        for (String fieldName : ignoredFields) {
            try {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
