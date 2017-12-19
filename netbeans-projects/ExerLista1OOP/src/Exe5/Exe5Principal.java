package Exe5;

import java.util.Scanner;

public class Exe5Principal {

    public Exe5Principal() {
        Scanner scan = new Scanner(System.in);
        Controle controle = new Controle(3);

        Estado santaCatarina = new Estado();
        santaCatarina.setNome("Santa Catarina");
        Cidade[] cidadesSC = new Cidade[3];

        Cidade indaial = new Cidade();
        indaial.setNome("Indaial");
        Dados dadosInd = new Dados();
        dadosInd.setPopulacao("População: 70 mil");
        dadosInd.setFesta("Principal Festa: FIMI - aniversário de indaial");
        dadosInd.setIdh("IDH: 12");
        indaial.setDados(dadosInd);
        cidadesSC[0] = indaial;

        Cidade blumenau = new Cidade();
        blumenau.setNome("Blumenau");
        Dados dadosBlu = new Dados();
        dadosBlu.setPopulacao("População: 300 mil");
        dadosBlu.setFesta("Principal Festa: October Fest");
        dadosBlu.setIdh("IDH: 10");
        blumenau.setDados(dadosBlu);
        cidadesSC[1] = blumenau;

        Cidade navegantes = new Cidade();
        navegantes.setNome("Navegantes");
        Dados dadosNav = new Dados();
        dadosNav.setPopulacao("População: 65 mil");
        dadosNav.setFesta("Principal Festa: Carnaval");
        dadosNav.setIdh("IDH: 9");
        navegantes.setDados(dadosNav);
        cidadesSC[2] = navegantes;

        santaCatarina.setCidades(cidadesSC);
        try {
            controle.addEstado(santaCatarina);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        Estado saoPaulo = new Estado();
        saoPaulo.setNome("São Paulo");
        Cidade[] cidadeSP = new Cidade[3];

        Cidade cidSaoPaulo = new Cidade();
        cidSaoPaulo.setNome("São Paulo");
        Dados dadosSP = new Dados();
        dadosSP.setPopulacao("População: 10 milhões");
        dadosSP.setFesta("Principal Festa: Carnaval");
        dadosSP.setIdh("IDH: 20");
        cidSaoPaulo.setDados(dadosSP);
        cidadeSP[0] = cidSaoPaulo;

        Cidade campinas = new Cidade();
        campinas.setNome("Campinas");
        Dados dadosCam = new Dados();
        dadosCam.setPopulacao("População: 2 milhões");
        dadosCam.setFesta("Principal Festa: Rodeio");
        dadosCam.setIdh("IDH: 15");
        campinas.setDados(dadosCam);
        cidadeSP[1] = campinas;

        Cidade santos = new Cidade();
        santos.setNome("Santos");
        Dados dadosSan = new Dados();
        dadosSan.setPopulacao("População: 900 mil");
        dadosSan.setFesta("Principal Festa: Carnaval");
        dadosSan.setIdh("IDH: 9");
        santos.setDados(dadosSan);
        cidadeSP[2] = santos;

        saoPaulo.setCidades(cidadeSP);
        try {
            controle.addEstado(saoPaulo);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        Estado rioDeJaneiro = new Estado();
        rioDeJaneiro.setNome("Rio de Janeiro");
        Cidade[] cidadeRJ = new Cidade[3];

        Cidade cidRioDeJaneiro = new Cidade();
        cidRioDeJaneiro.setNome("Rio de Janeiro");
        Dados dadosRJ = new Dados();
        dadosRJ.setPopulacao("População: 7 milhões");
        dadosRJ.setFesta("Principal Festa: Carnaval");
        dadosRJ.setIdh("IDH: 20");
        cidRioDeJaneiro.setDados(dadosRJ);
        cidadeRJ[0] = cidRioDeJaneiro;

        Cidade macae = new Cidade();
        macae.setNome("Macaé");
        Dados dadosMac = new Dados();
        dadosMac.setPopulacao("População: 400 mil");
        dadosMac.setFesta("Principal Festa: Carnaval");
        dadosMac.setIdh("IDH: 18");
        macae.setDados(dadosMac);
        cidadeRJ[1] = macae;

        Cidade niteroi = new Cidade();
        niteroi.setNome("Niteroi");
        Dados dadosNit = new Dados();
        dadosNit.setPopulacao("População: 500 mil");
        dadosNit.setFesta("Principal Festa: Carnaval");
        dadosNit.setIdh("IDH: 7");
        niteroi.setDados(dadosNit);
        cidadeRJ[2] = niteroi;

        rioDeJaneiro.setCidades(cidadeRJ);
        try {
            controle.addEstado(rioDeJaneiro);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println("Dados e eventos de cidades");
        System.out.println(controle.getEstados());
        System.out.println("\nEntre com um estado:");
        String cidades = controle.getCidades(scan.nextLine());
        System.out.println(cidades);
        if (!cidades.equals("Estado não encontrado")) {
            System.out.println("\nEntre com uma cidade:");
            System.out.println(controle.getDados(scan.nextLine()));
        }
    }
}
