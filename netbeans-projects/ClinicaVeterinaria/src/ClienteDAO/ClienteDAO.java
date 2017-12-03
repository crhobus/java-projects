package ClienteDAO;

import ClinicaVeterinaria.Cliente;

public interface ClienteDAO {

    public void gravar(Cliente cliente);

    public Cliente ler(int id);
}
