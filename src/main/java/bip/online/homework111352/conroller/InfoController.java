package bip.online.homework111352.conroller;

import bip.online.homework111352.service.InfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService service;
    @Value("${server.port}")
    private String port;

    public InfoController(InfoService service) {
        this.service = service;
    }

    @GetMapping("/port")
    public ResponseEntity getPort(){
        return ResponseEntity.ok(port);
    }

    @GetMapping("/sums")
    public ResponseEntity getSum(){

        return ResponseEntity.ok(service.getSum());
    }

}
