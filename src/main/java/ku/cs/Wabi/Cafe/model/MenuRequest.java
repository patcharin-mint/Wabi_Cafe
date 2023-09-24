//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.model;

import lombok.Data;

import java.util.UUID;


@Data
public class MenuRequest {
    private String name;
    private UUID categoryId;
    private double price;
}
