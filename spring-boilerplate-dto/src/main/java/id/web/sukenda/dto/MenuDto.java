package id.web.sukenda.dto;

import id.web.sukenda.common.utils.DTOEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto implements Serializable, DTOEntity {

    private String id;

    private String name;

    private String url;

}
