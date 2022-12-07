package thi.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import thi.app.model.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("FROM Product p WHERE p.subCategory.category.id = ?1")
    public List<Product> getProductByCategoryId(Long cid);

    @Query("FROM Product p WHERE p.subCategory.id = ?1")
    public List<Product> getProductBySubCategoryId(Long cid);

    @Query(value = "select * from orange_os.tb_products where id like %:key% or lower(name) like %:key%", nativeQuery = true)
    public List<Product> findProductsByNameLikeOrIdLike(@Param("key") String key);
}