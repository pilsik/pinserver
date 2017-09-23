package by.sivko.pinserver.models.repositories;

import by.sivko.pinserver.models.Pin;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PinRepository extends Repository<Pin, Long> {
    Optional<Pin> findOne(Long id);
    Pin save(Pin pin);
    List<Pin> findAll();
}
