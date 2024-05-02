package br.com.hcgv.carsparadise.carsparadise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record Veiculo( String TipoVeiculo,
                       String  Valor,
                       String  Marca,
                       String  Modelo,
                       String  AnoModelo,
                       String  Combustivel,
                       String  CodigoFipe ,
                       String  MesReferencia,
                       String  SiglaCombustivel ) {
}
