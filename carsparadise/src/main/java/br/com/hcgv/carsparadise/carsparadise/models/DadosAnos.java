package br.com.hcgv.carsparadise.carsparadise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAnos(String codigo, String nome) {
}
