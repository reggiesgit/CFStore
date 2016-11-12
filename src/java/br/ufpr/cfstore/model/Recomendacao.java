package br.ufpr.cfstore.model;

import br.ufpr.cfstore.jdbc.DBConnector;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mahout.cf.taste.common.TasteException;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

/**
 *
 * @author leandro
 */
public class Recomendacao {

    public static void main(String[] args) {
        Connection con = null;
        String sql = "INSERT INTO Recomendacao (item, itemRecomendado, score) VALUES(?,?,?)";
        PreparedStatement pstmt;
        try {
            con = DBConnector.getConnection();
            pstmt = con.prepareStatement(sql);
            DataModel dm = new FileDataModel(new File("web/data/cfstore.csv"));
            ItemSimilarity is = new LogLikelihoodSimilarity(dm);
            GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, is);

            for (LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) {
                long itemId = items.nextLong();
                List<RecommendedItem> recomendacoes = recommender.mostSimilarItems(itemId, 5);

                for (RecommendedItem recomendacao : recomendacoes) {
                    //System.out.println(itemId + ", " + recommendation.getItemID() + ", " + recommendation.getValue());
                    pstmt.setLong(1, itemId);
                    pstmt.setLong(2, recomendacao.getItemID());
                    pstmt.setFloat(3, recomendacao.getValue());
                    pstmt.executeUpdate();
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Recomendacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TasteException ex) {
            Logger.getLogger(Recomendacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Recomendacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Recomendacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
