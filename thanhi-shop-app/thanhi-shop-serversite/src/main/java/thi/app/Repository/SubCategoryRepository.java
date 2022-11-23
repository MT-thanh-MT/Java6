package thi.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thi.app.model.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}