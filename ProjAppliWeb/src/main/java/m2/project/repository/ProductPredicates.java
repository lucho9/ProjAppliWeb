package m2.project.repository;

import m2.project.model.QProduct;

import com.mysema.query.types.Predicate;

 
/**

 */
public class ProductPredicates {
 
    public static Predicate nameIsLike(final String searchTerm) {
        QProduct product = QProduct.product;
        return product.name.contains(searchTerm);
    }
}