package epheluv.agri.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SensorRepository extends JpaRepository<SensorData, Long> {

    // 基础查询：获取指定区域的最新N条数据
    @Query("SELECT s FROM SensorData s " +
           "WHERE s.sensorType = :type AND s.zoneId = :zone " +
           "ORDER BY s.timestamp DESC")
    List<SensorData> findLatestByZone(
        @Param("type") String type,
        @Param("zone") String zoneId,
        Pageable pageable
    );

    // 区域聚合查询：获取所有区域最新数据
    @Query("SELECT s FROM SensorData s " +
           "WHERE s.sensorType = :type AND s.id IN (" +
           "  SELECT MAX(s2.id) FROM SensorData s2 " +
           "  WHERE s2.sensorType = :type " +
           "  GROUP BY s2.zoneId" +
           ")")
    List<SensorData> findLatestAllZones(@Param("type") String type);

    // 历史趋势分析
    @Query("SELECT DATE_FORMAT(s.timestamp, '%Y-%m-%d %H:00:00') as period, " +
           "AVG(s.value) as avgValue, s.zoneId " +
           "FROM SensorData s " +
           "WHERE s.sensorType = :type AND s.timestamp >= :start " +
           "GROUP BY period, s.zoneId " +
           "ORDER BY period DESC")
    List<Object[]> analyzeHistoricalTrend(
        @Param("type") String type,
        @Param("start") LocalDateTime startTime
    );
}
