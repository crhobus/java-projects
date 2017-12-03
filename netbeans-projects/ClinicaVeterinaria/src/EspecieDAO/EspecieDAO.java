package EspecieDAO;

import ClinicaVeterinaria.Especie;

public interface EspecieDAO {

    public void gravar(Especie especie);

    public Especie ler(int id);
}
