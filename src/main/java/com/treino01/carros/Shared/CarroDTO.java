package com.treino01.carros.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarroDTO {

    private Long registro;
    private Integer ano;
    private String marca;
    private String modelo;
    private String cor;
}
