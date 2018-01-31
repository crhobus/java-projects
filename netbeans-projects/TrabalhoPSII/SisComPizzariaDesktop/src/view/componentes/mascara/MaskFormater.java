package view.componentes.mascara;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class MaskFormater {

    public static DefaultFormatterFactory getMaskFormater(int tpMascara) {
        MaskFormatter formato = new MaskFormatter();
        try {
            switch (tpMascara) {
                case 1://Formato data
                    formato.setMask("##/##/####");
                    formato.setPlaceholderCharacter('_');
                    break;
                case 2://Formato Telefone
                    formato.setMask("(##)####-####");
                    formato.setPlaceholderCharacter('_');
                    break;
                case 3://Formato Cep
                    formato.setMask("#####-###");
                    formato.setPlaceholderCharacter('_');
                    break;
                case 4://Formato RG
                    formato.setMask("#.###.###");
                    formato.setPlaceholderCharacter('_');
                    break;
                case 5://Formato CPF
                    formato.setMask("###.###.###-##");
                    formato.setPlaceholderCharacter('_');
                    break;
                case 6://Formato Carteira de Trabalho / Série
                    formato.setMask("####### / ###-#");
                    formato.setPlaceholderCharacter('_');
                    break;
                case 7://CNH
                    formato.setMask("###########");
                    formato.setPlaceholderCharacter('_');
                    break;
                case 8://Formato Pis/Pasep
                    formato.setMask("###.#####.##-#");
                    formato.setPlaceholderCharacter('_');
                    break;
                case 9: // Formato de valor com 2 decimais(Ex: 10.00)
                    formato.setMask("######.##");
                    formato.setPlaceholderCharacter('_');
                    break;
            }
        } catch (Exception ex) {}
        return new DefaultFormatterFactory(formato);
    }
}