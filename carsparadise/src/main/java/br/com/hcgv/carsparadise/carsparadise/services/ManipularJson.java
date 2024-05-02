package br.com.hcgv.carsparadise.carsparadise.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ManipularJson implements  ImanipularJson{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertJson(String json, Class<T> classe) {

        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao tentar converter o json em classe");
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {

        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);  // COLETAR E FORMAR UMA LISTA COM OS DADOS
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
