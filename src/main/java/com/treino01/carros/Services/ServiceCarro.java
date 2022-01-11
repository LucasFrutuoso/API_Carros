package com.treino01.carros.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.treino01.carros.Model.Carro;
import com.treino01.carros.Repositories.CarroRepository;
import com.treino01.carros.Shared.CarroDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCarro {

    @Autowired
    CarroRepository repository;

    public List<CarroDTO> consultar() {

        List<Carro> carros = repository.findAll();

        return carros.stream().map(c -> new ModelMapper().map(c, CarroDTO.class))
                .collect(Collectors.toList());

    }

    public Optional<CarroDTO> consultarPorId(Long registro) {

        Optional<Carro> carros = repository.findById(registro);

        CarroDTO carro = new ModelMapper().map(carros.get(), CarroDTO.class);

        return Optional.of(carro);

    }

    public CarroDTO adicionar(CarroDTO carroDto) {

        carroDto.setRegistro(null);

        ModelMapper mapper = new ModelMapper();

        Carro carros = mapper.map(carroDto, Carro.class);

        carros = repository.save(carros);

        carroDto.setRegistro(carros.getRegistro());

        return carroDto;
    }


    public void deletar(Long registro){

        repository.deleteById(registro);

    }


    public CarroDTO atualizar(Long registro, CarroDTO carroDto){

        carroDto.setRegistro(registro);

        ModelMapper mapper = new ModelMapper();

        Carro carros = mapper.map(carroDto, Carro.class);

        repository.save(carros);

        return carroDto;



    }

}
