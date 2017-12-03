package ExameDAO;

import ClinicaVeterinaria.Exame;

public interface ExameDAO {

    public void gravar(Exame exame);

    public Exame ler(int id);
}
