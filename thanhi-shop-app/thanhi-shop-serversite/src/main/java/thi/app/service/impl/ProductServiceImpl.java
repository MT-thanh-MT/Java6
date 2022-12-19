package thi.app.service.impl;

import joptsimple.internal.Strings;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.Repository.ProductRepository;
import thi.app.model.dto.ProductDTO;
import thi.app.model.entity.Account;
import thi.app.model.entity.Product;
import thi.app.model.mapper.ProductMapper;
import thi.app.service.IProductService;
import thi.app.web.errors.ProductAlreadyExistsException;
import thi.app.web.errors.ProductNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductMapper productMapper;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private static final List<String> SEARCHABLE_FIELDS = Arrays.asList("name", "createBy");

    @Override
    public List<ProductDTO> searchProduct(String text, List<String> fields, int limit) {

        List<String> fieldsToSearchBy = fields.isEmpty() ? SEARCHABLE_FIELDS : fields;

        boolean containsInvalidField = fieldsToSearchBy.stream().anyMatch(f -> !SEARCHABLE_FIELDS.contains(f));

        if (containsInvalidField) {
            throw new IllegalArgumentException();
        }

        List<Product> list = null;

        if (Strings.isNullOrEmpty(text)) {
            list = productRepo.findAll();
        } else {
            list = productRepo.searchBy(text, limit, fields.toArray(new String[0]));
        }

        return productMapper.toDto(list);
    }

    @Override
    public void indexData() throws InterruptedException {
        EntityManager em = emf.createEntityManager();
        SearchSession searchSession = Search.session(em);
        MassIndexer indexer = searchSession.massIndexer(Product.class)
                .threadsToLoadObjects(7);
        indexer.startAndWait();
    }

    @Override
    public List<ProductDTO> getAll() {
        return productMapper.toDto(productRepo.findAll());
    }

    @Override
    public List<ProductDTO> getByCategory(Long cid) {
        return productMapper.toDto(productRepo.getProductByCategoryId(cid));
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        if (productDTO.getId() != null) {
            throw new ProductAlreadyExistsException("This product already exists! check product id and try again.");
        } else {
            Product product = productMapper.toEntity(productDTO);
            product = productRepo.save(product);
            return productMapper.toDto(product);
        }
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        if (productDTO.getId() == null || productDTO.getId() <= 0) {
            throw new ProductAlreadyExistsException("This product not exists! check product id and try again.");
        } else {
            Product oldProduct = productRepo.findById(productDTO.getId()).get();
            Product product = productMapper.toEntity(productDTO);
            product.setCreateBy(oldProduct.getCreateBy());
            product = productRepo.save(product);
            return productMapper.toDto(product);
        }
    }

    @Override
    public boolean delete(Long id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
            throw new ProductAlreadyExistsException("This product not exists! check product id and try again.");
        } else {
            productRepo.delete(product);
            return true;
        }
    }

    @Override
    public List<ProductDTO> getProductByNameOrId(Optional<String> key) {
        List<Product> list = new ArrayList<>();
        if (key.isPresent()){
            list = productRepo.findProductsByNameLikeOrIdLike(key.get().toLowerCase());
        } else if (key.get().isEmpty()){
            list = productRepo.findAll();
        }
        return productMapper.toDto(list);
    }

    @Override
    public List<ProductDTO> getBySubCategory(Long scid) {
        return productMapper.toDto(productRepo.getProductBySubCategoryId(scid));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        ProductDTO product = productMapper.toDto(productRepo.findById(id).orElse(null));
        if (product == null) {
            throw new ProductNotFoundException("This product was not found!");
        } else {
            return product;
        }
    }
}
