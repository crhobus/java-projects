package prototipojena;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import java.io.InputStream;

public class Negocio {

    private String caminhoOntologia;
    private Model modelo;

    public Negocio() {
        caminhoOntologia = "";
    }

    public String ver() {
        String s = "";
        try {
            ResIterator listSubjects = modelo.listSubjects();
            while (listSubjects.hasNext()) {
                Resource nextResource = listSubjects.nextResource();
                StmtIterator listProperties = nextResource.listProperties();
                boolean ehPropriedade = false;
                while (listProperties.hasNext()) {
                    Statement nextStatement = listProperties.nextStatement();
                    if (nextStatement.asTriple().getObject().isURI() && !ehPropriedade) {
                        if (nextStatement.asTriple().getObject().getLocalName().toString().equals("NamedIndividual")) {
                            s += "\n\nSujeito: " + nextStatement.asTriple().getSubject().getLocalName();
                        } else {
                            if (nextStatement.asTriple().getObject().toString().contains("#")) {
                                s += "\n	subtipo de:" + nextStatement.asTriple().getObject().getLocalName();
                            }
                        }
                    } else {
                        if (!nextStatement.asTriple().getObject().toString().contains("#")) {
                            ehPropriedade = true;
                            s += "\n\n Propriedade: " + nextStatement.asTriple().getSubject().getLocalName();
                        } else {
                            if (nextStatement.asTriple().getPredicate().toString().contains("range")) {
                                s += "\n	- " + nextStatement.asTriple().getObject().getLocalName();
                            } else if (nextStatement.asTriple().getPredicate().toString().contains("domain")) {
                                s += "\n	para " + nextStatement.asTriple().getObject().getLocalName();
                                ehPropriedade = false;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            return s.substring(2);
        }
    }

    public String listarPropriedades(int qtdTab, ReifiedStatement reifiedStatement) {
        String ret = "";
        String tabs = "";
        StmtIterator listProperties = reifiedStatement.listProperties();

        for (int i = 0; i < qtdTab; i++) {
            tabs += "	";
        }

        while (listProperties.hasNext()) {
            ReifiedStatement createReifiedStatement = listProperties.nextStatement().createReifiedStatement();
            if (createReifiedStatement != null) {
                ret += listarPropriedades(qtdTab + 1, createReifiedStatement);
            }
        }

        ret += tabs + listProperties.toString();

        return ret;
    }

    public void carregarOntologia(String caminho) {
        caminhoOntologia = caminho;
        modelo = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(caminhoOntologia);
        modelo.read(in, null);
    }
}
