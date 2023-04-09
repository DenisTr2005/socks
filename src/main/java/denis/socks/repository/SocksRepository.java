package denis.socks.repository;

import denis.socks.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocksRepository extends JpaRepository<Socks,Long> {
    Socks findByColorAndCottonPart(String color, int cottonPart);

    List<Socks> findByColorAndCottonPartIsGreaterThan(String color, int cottonPart);

    List<Socks> findByColorAndCottonPartIsLessThan(String color, int cottonPart);
}
