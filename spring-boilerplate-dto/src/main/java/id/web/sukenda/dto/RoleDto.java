package id.web.sukenda.dto;

import id.web.sukenda.common.utils.DTOEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable, DTOEntity {

    private String id;

    private String name;

    private String code;

    private List<MenuDto> menus;

}
