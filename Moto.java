public final class Moto extends Veiculo {
    private boolean partidaEletrica;

    public Moto(String marca, String modelo, int ano, String placa, boolean partidaEletrica) {
        super(marca, modelo, ano, placa);
        this.partidaEletrica = partidaEletrica;
    }

    public boolean getPartidaEletrica() {
        return partidaEletrica;
    }

    public void setPartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;
    }

    @Override
    public String toString() {
        var partida = this.getPartidaEletrica() ? "Sim" : "Não";
        return "Veículo: " + this.getMarca()
                + " " + this.getModelo()
                + " " + this.getAno()
                + " - Part. Elétrica: " + partida;
    }
    @Override
    public double calcularImposto() {
        return 500.0;
    }
}
