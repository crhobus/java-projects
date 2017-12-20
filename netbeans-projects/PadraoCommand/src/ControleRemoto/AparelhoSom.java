package ControleRemoto;

public class AparelhoSom {

    private boolean verificaStatusApa = false, colocarCD = false, tocarMusicasCD = false, ligarRadio;
    private int volume;

    public void ligar() {
        if (verificaStatusApa == false) {
            System.out.println("O Aparelho de som está ligado");
            verificaStatusApa = true;
        } else {
            System.out.println("O aparelho de som não pode ser ligado, pois ja está ligado");
        }
    }

    public void desligar() {
        if (verificaStatusApa == true) {
            System.out.println("O Aparelho de som está desligado");
            verificaStatusApa = false;
        } else {
            System.out.println("O aparelho de som não pode ser desligado, pois já está desligado");
        }
    }

    public void colocarCD() {
        if (verificaStatusApa == true) {
            if (colocarCD == true) {
                System.out.println("O cd não pode ser colocado pois ja há outro cd");
            } else {
                System.out.println("Colocando cd no aparelho");
                colocarCD = true;
            }
        } else {
            System.out.println("Ligue o aparelho primeiro");
        }
    }

    public void retirarCD() {
        if (verificaStatusApa == true) {
            if (colocarCD == true) {
                System.out.println("O cd foi retirado");
                colocarCD = false;
            } else {
                System.out.println("Não há cd no aparelho");
            }
        } else {
            System.out.println("Ligue o aparelho primeiro");
        }
    }

    public void tocarMusicasCD() {
        if (verificaStatusApa == true) {
            if (colocarCD == true) {
                if (ligarRadio == true) {
                    System.out.println("Desligue o radio primeiro");
                } else {
                    System.out.println("Tocando Musica do CD..........................");
                    tocarMusicasCD = true;
                }
            } else {
                System.out.println("Não há cd no aparelho");
            }
        } else {
            System.out.println("Ligue o aparelho primeiro");
        }
    }

    public void paraTocarMusicaCD() {
        if (verificaStatusApa == true) {
            if (colocarCD == true) {
                if (tocarMusicasCD == true) {
                    System.out.println("Parou de tocar musica");
                    tocarMusicasCD = false;
                } else {
                    System.out.println("Não há musicas tocando");
                }
            } else {
                System.out.println("Não há cd no aparelho");
            }
        } else {
            System.out.println("Ligue o aparelho primeiro");
        }
    }

    public void ligarRadio() {
        if (verificaStatusApa == true) {
            if (tocarMusicasCD == false) {
                if (ligarRadio == false) {
                    System.out.println("Ligando o radio");
                    ligarRadio = true;
                } else {
                    System.out.println("O radio já esta desligado");
                }
            } else {
                System.out.println("Desligue o cd primeiro");
            }
        } else {
            System.out.println("Ligue o aparelho primeiro");
        }
    }

    public void desligarRadio() {
        if (verificaStatusApa == true) {
            if (ligarRadio == true) {
                System.out.println("Desligando o radio");
                ligarRadio = false;
            } else {
                System.out.println("O radio ja está desligado");
            }
        } else {
            System.out.println("Ligue o aparelho primeiro");
        }
    }

    public void aumentarVolume() {
        if (verificaStatusApa == true) {
            if (tocarMusicasCD == true || ligarRadio == true) {
                volume++;
                System.out.println("Volume: " + volume);
            } else {
                System.out.println("Não não musicas tocando");
            }
        } else {
            System.out.println("Ligue o aparelho primeiro");
        }
    }

    public void diminuirVolume() {
        if (verificaStatusApa == true) {
            if (tocarMusicasCD == true || ligarRadio == true) {
                volume--;
                System.out.println("Volume: " + volume);
            } else {
                System.out.println("Não não musicas tocando");
            }
        } else {
            System.out.println("Ligue o aparelho primeiro");
        }
    }
}
