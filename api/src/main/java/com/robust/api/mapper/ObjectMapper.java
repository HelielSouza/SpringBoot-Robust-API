package com.robust.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.robust.api.data.dto.PersonResponseDto;
import com.robust.api.data.model.Person;


public class ObjectMapper {

	private static final ModelMapper modelMapper = new ModelMapper();
	
	static {
        modelMapper.addMappings(new PropertyMap<Person, PersonResponseDto>() {
        	@Override
        	protected void configure() {
        		map().setKey(source.getId());
        	}
        });
    }
	
//  GENERIC IMPLEMENTATION 
//	static {
//        modelMapper.typeMap(Object.class, Object.class)
//            .addMappings(mapper -> mapper.map(source -> getField(source, "id"), (destination, value) -> setField(destination, "key", value)));
//    }
//
//    private static Object getField(Object source, String fieldName) {
//        try {
//            var field = source.getClass().getDeclaredField(fieldName);
//            field.setAccessible(true);
//            return field.get(source);
//        } catch (Exception e) {
//            return null; 
//        }
//    }
//
//    private static void setField(Object destination, String fieldName, Object value) {
//        try {
//            var field = destination.getClass().getDeclaredField(fieldName);
//            field.setAccessible(true);
//            field.set(destination, value);
//        } catch (Exception e) {
//        }
//    }
	
	public static <D, E> D convertObject(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <D, E> List<D> convertList(List<E> entities, Class<D> dtoClass) {
        return entities.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }
}