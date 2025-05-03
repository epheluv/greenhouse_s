package epheluv.agri.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InspectionPathRepository extends JpaRepository<InspectionPath, Long> {
    // 添加关联查询方法
    @Query("SELECT p FROM InspectionPath p LEFT JOIN FETCH p.points WHERE p.id = :id")
    Optional<InspectionPath> findByIdWithPoints(@Param("id") Long id);

    // 查询所有路径并立即加载路径点
    @Query("SELECT DISTINCT p FROM InspectionPath p LEFT JOIN FETCH p.points")
    List<InspectionPath> findAllWithPoints();
}
