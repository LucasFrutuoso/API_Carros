package com.treino01.carros.View.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.treino01.carros.Services.ServiceCarro;
import com.treino01.carros.Shared.CarroDTO;
import com.treino01.carros.View.Model.Request;
import com.treino01.carros.View.Model.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carro")
public class CarroController {

    @Autowired
    private ServiceCarro service;

    @GetMapping
    public ResponseEntity<List<Response>> consultadorTodos() {

        List<CarroDTO> carros = service.consultar();

        ModelMapper mapper = new ModelMapper();

        List<Response> res = carros
                .stream()
                .map(dto -> mapper
                        .map(dto, Response.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{registro}")
    public ResponseEntity<Optional<Response>> consultarPorId(@PathVariable Long registro) {

        Optional<CarroDTO> dto = service.consultarPorId(registro);

        Response response = new ModelMapper()
                .map(dto.get(), Response.class);

        return new ResponseEntity<>(Optional.of(response), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> adicionar(@RequestBody Request request) {

        ModelMapper mapper = new ModelMapper();

        CarroDTO dto = mapper.map(request, CarroDTO.class);

        dto = service.adicionar(dto);

        return new ResponseEntity<>(mapper.map(dto, Response.class), HttpStatus.CREATED);

    }

    @DeleteMapping("/{registro}")
    public ResponseEntity<?> deletar(@PathVariable Long registro){
        service.deletar(registro);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping("/{registro}")
    public ResponseEntity<Response> atualizar(@RequestBody Request request, @PathVariable Long registro) {

        ModelMapper mapper = new ModelMapper();

        CarroDTO dto = mapper
                .map(request, CarroDTO.class);

        service.atualizar(registro, dto);

        return new ResponseEntity<>(mapper.map(dto, Response.class), HttpStatus.OK);

    }

}
