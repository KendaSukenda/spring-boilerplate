package id.web.sukenda.common.utils;

import org.modelmapper.ModelMapper;

public class DTOUtils {

    public static DTOEntity convertToDto(Object obj, DTOEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    public static Object convertToEntity(Object obj, DTOEntity mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }

}
