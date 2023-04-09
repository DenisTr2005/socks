package denis.socks.controller;

import denis.socks.model.Socks;
import denis.socks.service.Operation;
import denis.socks.service.SocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/socks/")
@RestController
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }
    @PostMapping("/income")
    public ResponseEntity<Socks> createSock(@RequestBody Socks sock) {
        if(sock == null || sock.getCottonPart()<0 || sock.getCottonPart()>100 || sock.getQuantity()<1) {
            return ResponseEntity.badRequest().build();
        }
        Socks createdSock = socksService.update(sock);
        if(createdSock == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(createdSock);
    }

    @PostMapping("/outcome")
    public ResponseEntity<Socks> deleteSock(@RequestBody Socks sock) {
        if(sock == null || sock.getCottonPart()<0 || sock.getCottonPart()>100 || sock.getQuantity()<1) {
            return ResponseEntity.badRequest().build();
        }
        Socks createdSock = socksService.delete(sock);
        if(createdSock == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(createdSock);
    }
    @GetMapping
    public ResponseEntity<Integer> getSocks(String color,
                                            Operation operation,
                                            Integer cottonPart) {
        if((color == null) || (cottonPart == null) || (operation == null) || cottonPart<0 || cottonPart>100) {
            return ResponseEntity.badRequest().build();
        }
        Integer quantity = socksService.getQuantity(color, operation, cottonPart);
        if(quantity == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(quantity);
    }
}
