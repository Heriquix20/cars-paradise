package br.com.hcgv.carsparadise.carsparadise.principal;

import br.com.hcgv.carsparadise.carsparadise.models.*;
import br.com.hcgv.carsparadise.carsparadise.services.ConsumoHttp;
import br.com.hcgv.carsparadise.carsparadise.services.ManipularJson;
import ch.qos.logback.core.encoder.JsonEscapeUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private Scanner sc = new Scanner(System.in);
    private ConsumoHttp comsumir = new ConsumoHttp();
    private ManipularJson manipular = new ManipularJson();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibirMenu() {
        String menu = """
                \n******* OPÇÕES *******
                SELECIONE UMA DAS TRÊS:
                Carros
                Motos 
                Caminhões
                """;
        System.out.println(menu);
        String opcao = sc.nextLine();
        String endereco, escolha;

        if (opcao.toLowerCase().contains("car")) {
            endereco = URL_BASE + "carros/marcas";
            escolha = "do carro";
        }
        else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
            escolha = "da moto";
        }
        else {
            endereco = URL_BASE + "caminhoes/marcas";
            escolha = "do caminhão";
        }

        System.out.println("\nMarcas " + escolha + ": \n");

        // IMPRIMINDO AS MARCAS DOS VEICULOS
        var json = comsumir.comsumirApi(endereco);
        var marcas = manipular.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                        .forEach(d -> System.out.println(" -- CÓDIGO: " + d.codigo() + " -- Marca: " + d.nome() ));


        // PEDINDO O COD DA MARCA ESCOLHIDA
        System.out.println("********************************************************************************************");
        System.out.println("Veja as opções na lista e digite o código da marca escolhida:\n");
        var codMarca = sc.nextLine();

        // ATUALIZANDO O ENDERECO
        endereco = endereco + "/" + codMarca + "/modelos";

        System.out.println("********************************************************************************************");
        System.out.println("\nModelos da marca solicitada:\n");

        // IMPRIMINDO OS MODELOS DOS VEICULOS
        json = comsumir.comsumirApi(endereco);
        var modelosLista = manipular.convertJson(json, DadosModelos.class);
        modelosLista.modelos().stream()
                        .sorted(Comparator.comparing(Dados::codigo))
                        .forEach(d -> System.out.println(" -- CÓDIGO: " + d.codigo() + " -- Modelo: " + d.nome()));


        // PEGANDO O NOME DO MODELO DO VEICULO
        System.out.println("********************************************************************************************");
        System.out.println("\nDigite o nome " + escolha + " que queira consultar os valores: \n");
        var nomeModelo = sc.nextLine();

        System.out.println("********************************************************************************************");


        // CRIANDO UMA LISTA DE MODELOS
        List<Modelo> modelosBruto = modelosLista.modelos().stream()
                .map(m -> new Modelo(m.nome(), m.codigo())
                ) .collect(Collectors.toList());


        // FAZENDO UM OPTIONAL VER SE O MODELO EXISTE
        Optional<Modelo> modeloBuscado = modelosBruto.stream()
                .filter(m -> m.getNome().toLowerCase().contains(nomeModelo.toLowerCase()))
                .findAny();


        // IMPRIMINDO OS MODELOS DA BUSCA
        if (modeloBuscado.isPresent()) {
            System.out.println("\nResultado da pesquisa por '" + nomeModelo + "' :\n");
            modelosBruto.stream()
                    .filter(m -> m.getNome().toLowerCase().contains(nomeModelo.toLowerCase()))
                    .sorted(Comparator.comparing(Modelo::getCodigo))
                    .forEach(m -> System.out.println(" -- CÓDIGO: " + m.getCodigo() + " -- Modelo: " + m.getNome()));

        } else {
            System.out.println("Modelo não encontrado!");
        }


        // OBTENDO O CODIGO DO MODELO
        System.out.println("********************************************************************************************");
        System.out.println("\nDigite o código do modelo desejado\n");
        var codModelo = sc.nextLine();


        // ATUALIZANDO O ENDERECO
        endereco = endereco + "/" + codModelo + "/anos";


        // CRIANDO UMA LISTA DE TODOS OS ANOS DO MODELO
        json = comsumir.comsumirApi(endereco);
        List<DadosAnos> listaAnos = manipular.obterLista(json, DadosAnos.class);

        List<Veiculo> veiculos = new ArrayList<>();


        // LOOP PARA IMPRIMIR TODOS OS ANOS DO MODELO DE VEICULO SOLICITADO
        for (int i = 0; i < listaAnos.size(); i++) {


            // ATUALIZANDO O ENDERECO
            String enderecosAnos = endereco + "/" + listaAnos.get(i).codigo();


            //BUSCANDO DADOS
            json = comsumir.comsumirApi(enderecosAnos);
            var veiculo = manipular.convertJson(json, Veiculo.class);

            // ADICIONANDO OS VEICULOS NA LISTA
            veiculos.add(veiculo);

        }

        System.out.println("********************************************************************************************");
        System.out.println("\nModelo solicitado em vários anos: ");

        // IMPRIMINDO A LISTA DE VEICULOS COM TODAS AS INFORMACOES
        for ( Veiculo v : veiculos) {
        System.out.println("\nTipoVeiculo: " + v.TipoVeiculo());
        System.out.println("Valor: " + v.Valor());
        System.out.println("Marca: " + v.Marca());
        System.out.println("Modelo: " + v.Modelo());
        System.out.println("AnoModelo: " + v.AnoModelo());
        System.out.println("Combustível " + v.Combustivel());
        System.out.println("CodigoFipe: " + v.CodigoFipe());
        System.out.println("MesReferencia: " + v.MesReferencia());
        System.out.println("SiglaCombustivel: " + v.SiglaCombustivel());
        }
    }


}
