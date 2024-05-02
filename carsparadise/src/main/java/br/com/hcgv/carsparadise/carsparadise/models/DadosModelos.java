package br.com.hcgv.carsparadise.carsparadise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(List<Dados> modelos) {
}
