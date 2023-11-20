package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {

    @Transactional
    @Query(value="SELECT * FROM springserverdb.product ORDER BY number_product_sold desc limit 8", nativeQuery = true)
    Iterable<Product> findTop8ByProductSold();
    Iterable<Product> findProductsByCategory_Room_RoomIdOrderByPriceDesc(Integer roomId);
    Iterable<Product> findProductsByCategory_Room_RoomIdOrderByPriceAsc(Integer roomId);
    Iterable<Product> findProductsByCategory_Room_RoomId(Integer roomId);
    @Transactional
    Iterable<Product> findProductsByNameContaining(String name);
    @Transactional
    Iterable<Product> findProductsByCategory(Category category);
    @Transactional
    Iterable<Product> findProductsByCategoryOrderByPriceDesc(Category category);
    @Transactional
    Iterable<Product> findProductsByCategoryOrderByPriceAsc(Category category);
    @Override
    Optional<Product> findById(Integer integer);
    Optional<Product> findProductByOrder_OrderId(Integer orderId);
    @Transactional
    /*@Query(value="SELECT product.product_id as productId, product.url_image as image, product.description as description," +
            " product.material as material, product.name as name, product.number_product_sold as numberProductSold," +
            " product.price as price, product.quantity as quantiy, product.size as size, " +
            "product.status as status, category.name as categoryName FROM springserverdb.product JOIN springserverdb.category " +
            "WHERE springserverdb.product.category_id = springserverdb.category.category_id;", nativeQuery = true)*/
    @Query(value="SELECT springserverdb.product.* FROM springserverdb.product " +
            "JOIN springserverdb.category " +
            "ON springserverdb.product.category_id = springserverdb.category.category_id " +
            "WHERE springserverdb.product.category_id = springserverdb.category.category_id;", nativeQuery = true)
    Iterable<Product> findAllProductsWithCategoryName();

}
