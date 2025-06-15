package com.projectone.common.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Single-entry generic mapper using ModelMapper.
 */
public class GenericMapper {
    
    private static final ModelMapper modelMapper = new ModelMapper();
    
    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    
    /**
     * Maps an object or a list of objects to the given DTO class.
     *
     * @param source      the source object or collection
     * @param targetClass the DTO class
     * @return mapped DTO or list of DTOs
     */
    @SuppressWarnings("unchecked")
    public static <T> Object mapToDto(Object source, Class<T> targetClass) {
        if (source == null) return null;
        
        if (source instanceof Collection<?>) {
            List<T> result = new ArrayList<>();
            for (Object item : (Collection<?>) source) {
                result.add(modelMapper.map(item, targetClass));
            }
            return result;
        } else {
            return modelMapper.map(source, targetClass);
        }
    }
}


