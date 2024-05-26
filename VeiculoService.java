import java.util.ArrayList;
import java.util.List;
import entities.Veiculo;
import entities.Carro;
import entities.Moto;

public class VeiculoService {
    private List<Veiculo> veiculosDB;

    public VeiculoService() {
        this.veiculosDB = new ArrayList<>();
    }

    public Veiculo save(Veiculo veiculo) throws Exception {
        if (veiculo == null || veiculo.getModelo().isEmpty() || veiculo.getMarca().isEmpty()
                || veiculo.getPlaca().isEmpty() || veiculo.getAno() <= 0) {
            throw new Exception("Todos os campos são obrigatórios e o ano deve ser maior que zero");
        }
        if (veiculo instanceof Carro && ((Carro) veiculo).getNumeroPortas() <= 0) {
            throw new Exception("Número de portas deve ser maior que zero");
        }
        for (Veiculo v : veiculosDB) {
            if (v.getPlaca().equals(veiculo.getPlaca())) {
                throw new Exception("Já existe um veículo com esta placa");
            }
        }
        veiculosDB.add(veiculo);
        return veiculo;
    }

    public List<Veiculo> listarTodosVeiculos() {
        return new ArrayList<>(veiculosDB);
    }

    public Veiculo pesquisarVeiculoPorPlaca(String placa) {
        return veiculosDB.stream().filter(v -> v.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    public void removerVeiculoPorPlaca(String placa) throws Exception {
        Veiculo veiculo = pesquisarVeiculoPorPlaca(placa);
        if (veiculo == null) {
            throw new Exception("Veículo não encontrado");
        }
        veiculosDB.remove(veiculo);
    }

    public List<Veiculo> getVeiculosDB() {
        return veiculosDB;
    }
}
