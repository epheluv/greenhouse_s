package epheluv.agri.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    // 查询未解决的告警
    List<Alert> findByStatus(String status);

    // 根据区域和类型查询未解决的告警
    List<Alert> findByZoneIdAndSensorTypeAndStatus(String zoneId, String sensorType, String status);

    @Query("SELECT a FROM Alert a WHERE a.triggeredAt >= :start")
    List<Alert> findByTriggeredAtAfter(
            @Param("start") LocalDateTime start,
            Sort sort);

    Long countByStatusAndTriggeredAtAfter(
            @Param("status") String status,
            @Param("after") LocalDateTime after);
}