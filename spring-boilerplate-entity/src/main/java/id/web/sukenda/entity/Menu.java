package id.web.sukenda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("menus")
public class Menu extends BaseEntity {

    @Indexed(unique = true)
    private String name;

    private String url;

    private String icon;

}
