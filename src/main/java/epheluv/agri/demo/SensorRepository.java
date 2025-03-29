package epheluv.agri.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findTop20BySensorTypeOrderByTimestampDesc(String sensorType);
}
