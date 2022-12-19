package thi.app.Repository;

import org.springframework.stereotype.Repository;
import thi.app.model.entity.SubCategory;

@Repository
public interface SubCategoryRepository extends SearchRepository<SubCategory, Long> {
}