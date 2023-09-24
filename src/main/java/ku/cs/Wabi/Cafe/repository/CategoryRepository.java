//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.repository;

import ku.cs.Wabi.Cafe.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
