import java.util.List;
import java.util.Scanner;

public class CadVeiculo {
    private static Scanner scan;
    private static VeiculoService veiculoService;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        veiculoService = new VeiculoService();
        int opcao;
        do {
            limparTela();
            System.out.println("Sistema de Gerenciamento de Frotas");
            System.out.println("Escolha uma das opções:");
            System.out.println("1 - Cadastrar Novo Veículo");
            System.out.println("2 - Listar todos os Veículos");
            System.out.println("3 - Pesquisar Veículo por Placa");
            System.out.println("4 - Remover Veículo");
            System.out.println("0 - Sair");
            opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1:
                    save();
                    break;
                case 2:
                    imprimirVeiculos();
                    break;
                case 3:
                    pesquisarVeiculo();
                    break;
                case 4:
                    removerVeiculo();
                    break;
                case 0:
                    System.out.println("Volte logo!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            System.out.println("Pressione Enter para continuar...");
            scan.nextLine();
        } while (opcao != 0);
    }

    public static void save() {
        Veiculo veiculoAdd;

        System.out.println("Qual o tipo de veículo");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        int tipoVeiculo = scan.nextInt();
        scan.nextLine();

        System.out.println("Digite a marca do veículo");
        String marca = scan.nextLine();
        System.out.println("Digite o modelo do veículo");
        String modelo = scan.nextLine();
        System.out.println("Digite o ano do veículo");
        int ano = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite a placa do veículo");
        String placa = scan.nextLine();

        if (tipoVeiculo == 1) {
            System.out.println("Digite o número de portas");
            int numeroPortas = scan.nextInt();
            scan.nextLine();
            veiculoAdd = new Carro(marca, modelo, ano, placa, numeroPortas);
        } else {
            System.out.println("A moto possui partida elétrica: 1-Sim, 2-Não");
            int partidaEletrica = scan.nextInt();
            scan.nextLine();
            boolean partida = partidaEletrica == 1;
            veiculoAdd = new Moto(marca, modelo, ano, placa, partida);
        }
        try {
            veiculoService.save(veiculoAdd);
            System.out.println("Veículo cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void imprimirVeiculos() {
        List<Veiculo> veiculos = veiculoService.getVeiculosDB();
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo);
            System.out.println("Tempo de uso: " + veiculo.calcularTempoDeUso() + " ano(s)");
            System.out.println(veiculo.calcularImposto());
        }
    }

    private static void pesquisarVeiculo() {
        System.out.println("Digite a placa do veículo:");
        String placa = scan.nextLine();
        Veiculo veiculo = veiculoService.pesquisarPorPlaca(placa);
        if (veiculo != null) {
            System.out.println(veiculo);
            System.out.println("Tempo de uso: " + veiculo.calcularTempoDeUso() + " ano(s)");
            System.out.println(veiculo.calcularImposto());
        } else {
            System.out.println("VEÍCULO NÃO ENCONTRADO.");
        }
    }

    private static void removerVeiculo() {
        System.out.println("Digite a placa do veículo a ser removido:");
        String placa = scan.nextLine();
        try {
            veiculoService.removerPorPlaca(placa);
            System.out.println("Veículo removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
