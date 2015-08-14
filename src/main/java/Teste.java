
import com.absoft.pedidovenda.model.Cliente;
import com.absoft.pedidovenda.model.Endereco;
import com.absoft.pedidovenda.model.TipoPessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Diego Arantes
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction trx = manager.getTransaction();
        trx.begin();
        Cliente cliente = new Cliente();

        cliente.setNome("João das Couves");
        cliente.setEmail("joao@dascouves.com");
        cliente.setDocumentoReceitaFederal("123.123.132-12");
        cliente.setTipo(TipoPessoa.FISICA);

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Louca");
        endereco.setNumero("666");
        endereco.setCidade("Paranavaí");
        endereco.setUf("PR");
        endereco.setCep("87720-000");

        endereco.setCliente(cliente);       //è necessário para CascadeType.All funcionar
        cliente.getEnderecos().add(endereco); //è necessário para CascadeType.All funcionar

        manager.persist(cliente);

        trx.commit();

    }

}
