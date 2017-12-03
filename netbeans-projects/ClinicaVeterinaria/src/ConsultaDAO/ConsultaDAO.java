package ConsultaDAO;

import ClinicaVeterinaria.Consulta;

public interface ConsultaDAO {

    public void gravar(Consulta consulta);

    public Consulta ler(int id);
}
