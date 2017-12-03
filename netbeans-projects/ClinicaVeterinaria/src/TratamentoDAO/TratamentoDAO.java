package TratamentoDAO;

import ClinicaVeterinaria.Tratamento;

public interface TratamentoDAO {

    public void gravar(Tratamento tratamento);

    public Tratamento ler(int id);
}
