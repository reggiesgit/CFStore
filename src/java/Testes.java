

import br.ufpr.cfstore.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Testes {
    public static void main(String args[]){
        
        int idProduto = 110463;
        Produto prod = new Produto();
        Produto p = new Produto();
        ArrayList <Produto> recomendacoes = new ArrayList<Produto>();
       
        try{
            System.out.println("Produto escolhido:");
            p = prod.retornaProduto(idProduto);
            System.out.println(p.getDescricao());
            System.out.println(p.getPrecoUnitario());
        }catch(ClassNotFoundException  ex){
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            recomendacoes = (ArrayList)p.buscarRecomendacoes(idProduto);
            System.out.println("--------------------------------------");
            System.out.println("Recomendações:");
            for (Produto temp : recomendacoes) {
                    System.out.println(temp.getDescricao());
                    System.out.println(temp.getPrecoUnitario());
                    System.out.println();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
