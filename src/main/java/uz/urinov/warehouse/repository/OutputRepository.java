package uz.urinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.urinov.warehouse.entity.Output;

import java.util.Optional;

public interface OutputRepository extends JpaRepository<Output, Integer> {

    @Query(value = "select * from output where id=(select max(id) from output)", nativeQuery = true)
    Optional<Output> getMaxId();
}
