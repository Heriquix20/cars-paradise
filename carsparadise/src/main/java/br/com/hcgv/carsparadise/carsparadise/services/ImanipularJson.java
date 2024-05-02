package br.com.hcgv.carsparadise.carsparadise.services;

import java.util.List;

public interface ImanipularJson {

    <T> T convertJson(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
