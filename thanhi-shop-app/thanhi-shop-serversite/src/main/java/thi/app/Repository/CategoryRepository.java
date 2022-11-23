package thi.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thi.app.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}