package VeterinarioDAO;

import ClinicaVeterinaria.Veterinario;

public interface VeterinarioDAO {

    public void gravar(Veterinario veterinario);

    public Veterinario ler(int id);
}
