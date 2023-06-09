package uz.urinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.urinov.warehouse.entity.Input;

import java.util.Optional;

@Repository
public interface InputRepository extends JpaRepository<Input, Integer> {
    @Query(value = "select * from input where id=(select max(id) from input)", nativeQuery = true)
    Optional<Input> getMaxId();
}
