//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
import java.util.UUID;


@Data
@Entity
public class Category {


    @Id
    @GeneratedValue
    private UUID id;


    private String name;


    @OneToMany(mappedBy = "category") // 1 category มีได้หลาย menu + เชื่อมด้วย category
    List<Menu> menus;
}

