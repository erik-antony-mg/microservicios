package com.usuario.service.feingclients;

import com.usuario.service.models.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "carro-servicio",path ="/api/carro" ,url = "http://localhost:8080")
public interface CarroFeignClient {
    @PostMapping("/crear")
    public Carro crearCarro(@RequestBody Carro carro);
}
