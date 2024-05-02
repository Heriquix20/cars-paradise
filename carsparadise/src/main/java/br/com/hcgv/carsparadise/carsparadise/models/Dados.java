package br.com.hcgv.carsparadise.carsparadise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(Integer codigo, String nome) {
}
