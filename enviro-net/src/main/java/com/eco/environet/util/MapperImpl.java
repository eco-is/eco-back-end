package com.eco.environet.util;

import com.eco.environet.education.dto.LectureDto;
import com.eco.environet.education.model.Lecture;
import com.eco.environet.users.dto.UserDto;
import com.eco.environet.users.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperImpl implements Mapper {

    private final ModelMapper modelMapper;

    public MapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.typeMap(Lecture.class, LectureDto.class).addMappings(mapper -> mapper.map(src -> src.getCreator().getId(), LectureDto::setCreatorId));
    }

    @Override
    public <T, U> U map(T source, Class<U> targetClass, String... ignoredFields) {
        U mappedObject = modelMapper.map(source, targetClass);
        ignoreFields(mappedObject, List.of(ignoredFields));
        return mappedObject;
    }

    @Override
    public <T, U> List<U> mapList(List<T> sourceList, Class<U> targetClass, String... ignoredFields) {
        return sourceList.stream()
                .map(source -> map(source, targetClass, ignoredFields))
                .collect(Collectors.toList());
    }

    @Override
    public <T, U> Page<U> mapPage(Page<T> sourcePage, Class<U> targetClass, String... ignoredFields) {
        List<U> content = mapList(sourcePage.getContent(), targetClass, ignoredFields);
        return new PageImpl<>(content, sourcePage.getPageable(), sourcePage.getTotalElements());
    }

    private void ignoreFields(Object object, List<String> ignoredFields) {
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
