
public class Pessoa {
    String nome = "";
    int idade = 0;
    String sexoDaPessoa = " ";
    String telefoneContato = " ";

    public Pessoa(String nome, int idade, String sexoDaPessoa, String telefoneContato) {
        this.nome = nome;
        this.idade = idade;
        this.sexoDaPessoa = sexoDaPessoa;
        this.telefoneContato = telefoneContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexoDaPessoa() {
        return sexoDaPessoa;
    }

    public void setSexoDaPessoa(String sexoDaPessoa) {
        this.sexoDaPessoa = sexoDaPessoa;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

}