package denis.socks.service;

import denis.socks.model.Socks;
import denis.socks.repository.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SocksService {
    private final SocksRepository socksRepository;
    Logger logger = LoggerFactory.getLogger(SocksService.class);
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public Socks update(Socks sock) {
        logger.info("Was invoked method for update socks");
        Socks createdSock = socksRepository.findByColorAndCottonPart(sock.getColor(),sock.getCottonPart());
        if (createdSock != null) {
            sock.setQuantity(sock.getQuantity()+createdSock.getQuantity());
            sock.setId(createdSock.getId());
        }
        return socksRepository.save(sock);
    }

    public Socks delete(Socks sock) {
        logger.info("Was invoked method for delete socks");
        Socks createdSock = socksRepository.findByColorAndCottonPart(sock.getColor(),sock.getCottonPart());
        if (createdSock != null && (createdSock.getQuantity()-sock.getQuantity())>=0) {
            sock.setQuantity(createdSock.getQuantity()-sock.getQuantity());
            sock.setId(createdSock.getId());
            return socksRepository.save(sock);
        }
        return null;
    }

    public Integer getQuantity(String color, Operation operation, int cottonPart) {
        return switch (operation) {
            case equal -> socksRepository.findByColorAndCottonPart(color, cottonPart).getQuantity();
            case moreThan -> socksRepository.findByColorAndCottonPartIsGreaterThan(color, cottonPart).
                    stream().
                    map(Socks::getQuantity).
                    mapToInt(i -> i).
                    sum();
            case lessThan -> socksRepository.findByColorAndCottonPartIsLessThan(color, cottonPart).
                    stream().
                    map(Socks::getQuantity).
                    mapToInt(i -> i).
                    sum();
        };
    }
}
