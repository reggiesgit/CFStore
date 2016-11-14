
package br.ufpr.cfstore.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
/**
 *
 * @author leandro
 */
public class RecomendacaoUsuario {
    public static void main(String args[]) throws IOException, TasteException{
        DataModel dm = new FileDataModel(new File("web/data/cfstore.csv"));
        System.out.println(dm.getItemIDsFromUser(9131));
        UserSimilarity us = new PearsonCorrelationSimilarity(dm);
        //UserNeighborhood un = new ThresholdUserNeighborhood(1.0,us,dm);
        
       // UserBasedRecommender recomendar = new GenericUserBasedRecommender(dm,un,us);
        UserNeighborhood un = new NearestNUserNeighborhood(3, us, dm);
        Recommender recomendador =  new GenericUserBasedRecommender(dm, un, us);
        Recommender cachingRecommender = new CachingRecommender(recomendador);
        List<RecommendedItem> recomendacoes =  recomendador.recommend(9131, 5);

        System.out.println(recomendacoes.size());
        for (RecommendedItem recommendation : recomendacoes) {
            System.out.println(recommendation.getValue());
         }
    }
}