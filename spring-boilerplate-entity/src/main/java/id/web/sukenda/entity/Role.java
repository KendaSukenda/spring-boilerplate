package id.web.sukenda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("roles")
public class Role extends BaseEntity {

    @Indexed(unique = true)
    private String name;

    private String code;

    @DBRef
    private List<Menu> menus;

}
