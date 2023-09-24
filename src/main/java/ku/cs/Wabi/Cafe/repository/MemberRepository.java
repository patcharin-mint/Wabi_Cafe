//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.repository;

import ku.cs.Wabi.Cafe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;


@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Member findByUsername(String username); //จะหาด้วย username
}
