package AnimalDAO;

import ClinicaVeterinaria.Animal;

public interface AnimalDAO {

    public void gravar(Animal animal);

    public Animal ler(int id);
}
