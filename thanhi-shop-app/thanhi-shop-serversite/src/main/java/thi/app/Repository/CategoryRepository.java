package thi.app.Repository;

import org.springframework.stereotype.Repository;
import thi.app.model.entity.Category;

@Repository
public interface CategoryRepository extends SearchRepository<Category, Long> {
}