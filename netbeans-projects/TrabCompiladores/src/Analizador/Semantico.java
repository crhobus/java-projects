package Analizador;

import java.util.ArrayList;
import java.util.HashMap;

public class Semantico implements Constants {

    private String codigo;
    private ArrayList<Tipo> tipos;
    private ArrayList<String> identificadores;
    private ArrayList<String> selecao;
    private ArrayList<String> repeticao;
    private int desvio;
    private String nomeArq, operador_relacional, nomePrograma;
    private HashMap<String, Tipo> tabelaSimbolos;
    private String opAcao28;

    public Semantico(String nomeArq) {
        desvio = 0;
        codigo = "";
        operador_relacional = "";
        nomePrograma = "";
        opAcao28 = "";
        tipos = new ArrayList<Tipo>();
        selecao = new ArrayList<String>();
        repeticao = new ArrayList<String>();
        identificadores = new ArrayList<String>();
        tabelaSimbolos = new HashMap<String, Tipo>();
        this.nomeArq = nomeArq.substring(0, nomeArq.lastIndexOf("."));
    }

    public void executeAction(int action, Token token) throws SemanticError {
        System.out.println("\n\n Ação #" + action + ", Token: " + token);
        switch (action) {
            case 1:
                acao1();
                break;
            case 2:
                acao2();
                break;
            case 3:
                acao3(token);
                break;
            case 4:
                acao4(token);
                break;
            case 5:
                acao5(token);
                break;
            case 6:
                acao6();
                break;
            case 7:
                acao7();
                break;
            case 8:
                acao8(token);
                break;
            case 9:
                acao9(token);
                break;
            case 10:
                acao10(token);
                break;
            case 11:
                acao11(token);
                break;
            case 12:
                acao12(token);
                break;
            case 13:
                acao13(token);
                break;
            case 14:
                acao14(token);
                break;
            case 15:
                acao15(token);
                break;
            case 16:
                acao16(token);
                break;
            case 17:
                acao17(token);
                break;
            case 18:
                acao18(token);
                break;
            case 19:
                acao19(token);
                break;
            case 20:
                acao20(token);
                break;
            case 21:
                acao21(token);
                break;
            case 22:
                acao22(token);
                break;
            case 23:
                acao23(token);
                break;
            case 24:
                acao24(token);
                break;
            case 25:
                acao25(token);
                break;
            case 26:
                acao26(token);
                break;
            case 27:
                acao27(token);
                break;
            case 28:
                acao28(token);
                break;
            case 29:
                acao29(token);
                break;
            case 30:
                acao30(token);
                break;
            case 31:
                acao31(token);
                break;
            case 32:
                acao32();
                break;
            case 33:
                acao33();
                break;
            case 34:
                acao34();
                break;
            case 35:
                acao35();
                break;
            case 36:
                acao36(token);
                break;
        }
        System.out.println("\n" + "codigo = \n" + codigo);
    }

    public String getCodigo() {
        return this.codigo;
    }

    private void addLinha(String linha) {
        codigo += linha + "\n";
    }

    private void empilha(Tipo tipo) {
        System.out.println("Empilhando: " + tipo);
        this.tipos.add(0, tipo);
    }

    private Tipo desempilha() throws SemanticError {
        System.out.println("Desempilhando: ");
        return this.tipos.remove(0);
    }

    private Tipo topo() {
        return this.tipos.get(0);
    }

    private void acao1() {
        this.codigo = "";
        addLinha(".assembly extern mscorlib {}");
        addLinha(".assembly " + this.nomeArq.substring(this.nomeArq.lastIndexOf("\\") + 1, this.nomeArq.length()) + "{}");
        addLinha(".module " + this.nomeArq.substring(this.nomeArq.lastIndexOf("\\") + 1, this.nomeArq.length()) + ".exe");
    }

    private void acao2() {
        addLinha("ret");
        addLinha("}");
        addLinha("}");
    }

    private void acao3(Token token) throws SemanticError {
        Tipo t = null;
        if (tabelaSimbolos.containsKey(token.getLexeme())) {
            t = tabelaSimbolos.get(token.getLexeme());
        } else {
            t = desempilha();
        }
        switch (t) {
            case INT:
                addLinha("call void [mscorlib]System.Console::Write(int64)");
                break;
            case FLOAT:
                addLinha("call void [mscorlib]System.Console::Write(float64)");
                break;
            case DATE:
                addLinha("call void [mscorlib]System.Console::Write(string)");
                break;
            case TIME:
                addLinha("call void [mscorlib]System.Console::Write(string)");
                break;
            case BOOLEAN:
                addLinha("call void [mscorlib]System.Console::Write(bool)");
                break;
            case STRING:
                addLinha("call void [mscorlib]System.Console::Write(string)");
                break;
        }
    }

    private void acao4(Token token) throws SemanticError {
        Tipo t1 = desempilha();
        Tipo t2 = desempilha();
        if (t1 == t2 && t1 == Tipo.BOOLEAN) {
            addLinha(" or ");
            empilha(Tipo.BOOLEAN);
        } else {
            throw new SemanticError("tipo incompatível", token.getPosition());
        }
    }

    private void acao5(Token token) throws SemanticError {
        Tipo t1 = desempilha();
        Tipo t2 = desempilha();
        if (t1 == t2 && t1 == Tipo.BOOLEAN) {
            addLinha(" and ");
            empilha(Tipo.BOOLEAN);
        } else {
            throw new SemanticError("tipo incompatível", token.getPosition());
        }
    }

    private void acao6() {
        if (topo() == Tipo.BOOLEAN) {
            addLinha("ldc.i4 1");
            empilha(Tipo.BOOLEAN);
        }
    }

    private void acao7() {
        if (topo() == Tipo.BOOLEAN) {
            addLinha("ldc.i4 0");
            empilha(Tipo.BOOLEAN);
        }
    }

    private void acao8(Token token) throws SemanticError {
        Tipo t = desempilha();
        if (t == Tipo.BOOLEAN) {
            addLinha(" not ");
            empilha(Tipo.BOOLEAN);
        } else {
            throw new SemanticError("tipo incompatível", token.getPosition());
        }
    }

    private void acao9(Token token) throws SemanticError {
        operador_relacional = token.getLexeme();
    }

    private void acao10(Token token) throws SemanticError {//implementar data e hora
        Tipo t1 = desempilha();
        Tipo t2 = desempilha();

        if (t1 == t2 || (t1 == Tipo.INT && t2 == Tipo.FLOAT) || (t2 == Tipo.INT && t1 == Tipo.FLOAT)) {
            if (operador_relacional.equals("==")) {
                addLinha("ceq");
            } else {
                if (operador_relacional.equals("!=")) {
                    addLinha("ceq");
                    addLinha("ldc.i4 0");
                    addLinha("ceq");
                } else {
                    if (operador_relacional.equals("<")) {
                        addLinha("clt");
                    } else {
                        if (operador_relacional.equals("<=")) {
                            addLinha("cgt");
                            addLinha("ldc.i4 0");
                            addLinha("ceq");
                        } else {
                            if (operador_relacional.equals(">")) {
                                addLinha("cgt");
                            } else {
                                if (operador_relacional.equals(">=")) {
                                    addLinha("clt");
                                    addLinha("ldc.i4 0");
                                    addLinha("ceq");
                                }
                            }
                        }
                    }
                }
            }
            empilha(Tipo.BOOLEAN);
        } else {
            throw new SemanticError("tipo incompatível", token.getPosition());
        }
    }

    private void acao11(Token token) throws SemanticError {//implementar data e hora
        Tipo t1 = desempilha();
        Tipo t2 = desempilha();
        if (t1 == t2 && (t1 == Tipo.INT || t1 == Tipo.FLOAT)) {
            addLinha(" add ");
            empilha(t1);
            return;
        }
        if ((t1 == Tipo.INT && t2 == Tipo.FLOAT) || (t2 == Tipo.INT && t1 == Tipo.FLOAT)) {
            addLinha(" add ");
            empilha(Tipo.FLOAT);
            return;
        }
        throw new SemanticError("tipo incompatível", token.getPosition());
    }

    private void acao12(Token token) throws SemanticError {//implementar data e hora
        Tipo t1 = desempilha();
        Tipo t2 = desempilha();
        if (t1 == t2 && (t1 == Tipo.INT || t1 == Tipo.FLOAT)) {
            addLinha(" sub ");
            empilha(t1);
            return;
        }
        if ((t1 == Tipo.INT && t2 == Tipo.FLOAT) || (t2 == Tipo.INT && t1 == Tipo.FLOAT)) {
            addLinha(" sub ");
            empilha(Tipo.FLOAT);
            return;
        }
        throw new SemanticError("tipo incompatível", token.getPosition());
    }

    private void acao13(Token token) throws SemanticError {
        Tipo t1 = desempilha();
        Tipo t2 = desempilha();
        if (t1 == t2 && (t1 == Tipo.INT || t1 == Tipo.FLOAT)) {
            addLinha(" mul ");
            empilha(t1);
            return;
        }
        if ((t1 == Tipo.INT && t2 == Tipo.FLOAT) || (t2 == Tipo.INT && t1 == Tipo.FLOAT)) {
            addLinha(" mul ");
            empilha(Tipo.FLOAT);
            return;
        }
        throw new SemanticError("tipo incompatível", token.getPosition());
    }

    private void acao14(Token token) throws SemanticError {
        Tipo t1 = desempilha();
        Tipo t2 = desempilha();
        if ((t1 == t2 && (t1 == Tipo.INT || t1 == Tipo.FLOAT))
                || ((t1 == Tipo.INT && t2 == Tipo.FLOAT) || (t2 == Tipo.INT && t1 == Tipo.FLOAT))) {
            addLinha(" div ");
            empilha(Tipo.FLOAT);
            return;
        }
        throw new SemanticError("tipo incompatível", token.getPosition());
    }

    private void acao15(Token token) throws SemanticError {
        Tipo t1 = desempilha();
        Tipo t2 = desempilha();
        if (t1 == t2 && t1 == Tipo.INT) {
            addLinha(" div.un ");
            empilha(t1);
            return;
        }
        throw new SemanticError("tipo incompatível", token.getPosition());
    }

    private void acao16(Token token) throws SemanticError {
        Tipo t1 = desempilha();
        Tipo t2 = desempilha();
        if (t1 == t2 && (t1 == Tipo.INT)) {
            addLinha(" rem ");
            empilha(Tipo.FLOAT);
            return;
        }
        throw new SemanticError("tipo incompatível", token.getPosition());
    }

    private void acao17(Token token) {
        addLinha("ldc.i8 " + token.getLexeme());
        empilha(Tipo.INT);
    }

    private void acao18(Token token) {
        addLinha("ldc.r8 " + token.getLexeme().replace(",", "."));
        empilha(Tipo.FLOAT);
    }

    private void acao19(Token token) throws SemanticError {//implementar data e hora
        //tratar
        try {
            empilha(Tipo.DATE);
            addLinha("ldstr " + "\"" + token.getLexeme() + "\"");
        } catch (Exception e) {
            throw new SemanticError(e.getMessage(), token.getPosition());
        }
    }

    private void acao20(Token token) throws SemanticError {//implementar data e hora
        try {
            empilha(Tipo.TIME);
            addLinha("ldstr " + "\"" + token.getLexeme() + "\"");
        } catch (Exception e) {
            throw new SemanticError(e.getMessage(), token.getPosition());
        }
    }

    private void acao21(Token token) throws SemanticError {
        addLinha("ldstr " + token.getLexeme());
        empilha(Tipo.STRING);
    }

    private void acao22(Token token) throws SemanticError {
        Tipo t = desempilha();
        if (t == Tipo.INT) {
            addLinha("ldc.i8 -1");
            addLinha("mul");
            empilha(t);
        } else if (t == Tipo.FLOAT) {
            addLinha("ldc.r8 -1");
            addLinha("mul");
            empilha(t);
        } else {
            throw new SemanticError("tipo incompatível", token.getPosition());
        }
    }

    private void acao23(Token token) throws SemanticError {
        if (token.getLexeme().equals(nomePrograma)) {///
            throw new SemanticError("identificador " + token.getLexeme() + " ja declarado", token.getPosition());
        }
        identificadores.add(token.getLexeme());
        if (tabelaSimbolos.containsKey(token.getLexeme())) {
            empilha(tabelaSimbolos.get(token.getLexeme()));
        }
    }

    private void acao24(Token token) throws SemanticError {
        if (tabelaSimbolos.containsKey(token.getLexeme())) {
            addLinha("ldloc " + token.getLexeme());
            empilha(tabelaSimbolos.get(token.getLexeme()));
        } else {
            throw new SemanticError("Identificador '" + token.getLexeme() + "' não declarado.", token.getPosition());
        }
    }

    private void acao25(Token token) {//lancar excessão
        String aux = "";
        String t = token.getLexeme();
        Tipo tp = null;
        if (t.equals("int")) {
            tp = Tipo.INT;
            aux = "int64 ";
        } else {
            if (t.equals("float")) {
                tp = Tipo.FLOAT;
                aux = "float64 ";
            } else {
                if (t.equals("date")) {
                    tp = Tipo.DATE;
                    aux = "string ";
                } else {
                    if (t.equals("time")) {
                        tp = Tipo.TIME;
                        aux = "string ";
                    } else {
                        if (t.equals("boolean")) {
                            tp = Tipo.BOOLEAN;
                            aux = "bool ";
                        } else {
                            if (t.equals("string")) {
                                tp = Tipo.STRING;
                                aux = "string ";
                            }
                        }
                    }
                }
            }
        }

        String id = "";
        for (int i = identificadores.size(); i > 0; i--) {
            empilha(tp);
            id += aux + identificadores.get(i - 1);
            if ((i - 1) > 0) {
                id += ", ";
            }
            tabelaSimbolos.put(identificadores.get(i - 1), topo());
        }
        addLinha(".locals (" + id + ")");
    }

    private void acao26(Token token) throws SemanticError {
        int cont = identificadores.size() - 1;
        while (cont > 0) {
            empilha(topo());
            addLinha("dup");
            cont--;
        }

        for (int i = identificadores.size(); i > 0; i--) {
            String id = identificadores.get(i - 1);
            if (topo() != this.tabelaSimbolos.get(id)) {
                throw new SemanticError("tipo inválido", token.getPosition());
            }
            addLinha(" stloc " + id);
        }
        identificadores.clear();
        tipos.clear();
    }

    private void acao27(Token token) throws SemanticError {
        if (!repeticao.isEmpty()) {
            addLinha("br " + repeticao.get(0));

        } else {
            throw new SemanticError("Encontrado 'exit' fora de bloco 'loop'", token.getPosition());
        }
    }

    private void acao28(Token token) throws SemanticError {//lancar exceção
        opAcao28 = token.getLexeme();
        if (!opAcao28.equals("=")) {
            addLinha("ldloc " + identificadores.get(identificadores.size() - 1));
        }
    }

    private void acao29(Token token) throws SemanticError {
        if (!tabelaSimbolos.containsKey(identificadores.get(identificadores.size() - 1))) {
            throw new SemanticError("Identiificador '" + identificadores.get(identificadores.size() - 1) + "' não declarado.", token.getPosition());
        } else if (opAcao28.equals("+=")) {
            addLinha("add");
        } else {
            if (opAcao28.equals("-=")) {
                addLinha("sub");
            }
        }
        if (desempilha() == desempilha()) {
            addLinha("stloc " + identificadores.get(identificadores.size() - 1));
            identificadores.clear();
        } else {
            throw new SemanticError("tipo invalido", token.getPosition());
        }

    }

    private void acao30(Token token) throws SemanticError {
        try {
            Tipo t = tabelaSimbolos.get(identificadores.get(identificadores.size() - 1));
            addLinha("call string[mscorlib]System.Console::ReadLine()");
            switch (t) {
                case INT:
                    addLinha("call int64[mscorlib]System.Int64::Parse(string)");
                    break;
                case FLOAT:
                    addLinha("call float64[mscorlib]System.Double::Parse(string)");
                    break;
                case DATE:
                    //addLinha("call value[mscorlib]System.DateTime::Parse(string)");
                    break;
                case TIME:
                    //addLinha("call value[mscorlib]System.DateTime::Parse(string)");
                    break;
                case BOOLEAN:
                    addLinha("call bool[mscorlib]System.Boolean::parse(string)");
                    break;
            }
            addLinha("stloc " + identificadores.get(identificadores.size() - 1));
        } catch (Exception e) {
            throw new SemanticError("identificador não encontrado", token.getPosition());
        }
    }

    private void acao31(Token token) throws SemanticError {
        if (desempilha() == Tipo.BOOLEAN) {
            /*lbIn.add(0,"lb" + desvio);			      
            desvio++;								
            lbOut.add(0,"lb" + desvio);				
            desvio++;	
            addLinha("brfalse " + lbOut.get(0));*/
            selecao.add(0, "lb" + desvio);
            desvio++;
            addLinha("brfalse " + selecao.get(0));
        } else {
            throw new SemanticError("Tipo incompativel para comparação", token.getPosition());
        }
    }

    private void acao32() {
        addLinha(selecao.remove(0) + ":");
    }

    private void acao33() {
        String s = selecao.remove(0);
        selecao.add(0, "lb" + desvio);
        desvio++;
        addLinha("br " + selecao.get(0));
        addLinha(s + ":");
    }

    private void acao34() {
        repeticao.add(0, "lb" + desvio);
        addLinha(repeticao.get(0) + ":");
        desvio++;
        repeticao.add(0, "lb" + desvio);
        desvio++;
    }

    private void acao35() {
        String s = repeticao.remove(0) + ":";
        addLinha("br " + repeticao.remove(0));
        addLinha(s);
    }

    private void acao36(Token token) {
        nomePrograma = token.getLexeme();
        addLinha(".class public " + token.getLexeme() + " {");
        addLinha(".method static public void principal()");
        addLinha("{ .entrypoint");
    }
}
