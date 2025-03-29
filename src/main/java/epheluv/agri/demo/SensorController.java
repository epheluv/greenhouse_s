package epheluv.agri.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SensorController {
    private static final Set<String> VALID_SENSOR_TYPES = Set.of(
        "temperature", "humidity", "pressure", "co2"
    );

    private final SensorRepository repository;

    //=== 专用接口 ===//
    @GetMapping("/api/temperature")
    public List<SensorData> getTemperature() {
        return getByType("temperature");
    }

    @GetMapping("/api/humidity")
    public List<SensorData> getHumidity() {
        return getByType("humidity");
    }

    @GetMapping("/api/pressure")
    public List<SensorData> getPressure() {
        return getByType("pressure");
    }

    @GetMapping("/api/co2")
    public List<SensorData> getCo2() {
        return getByType("co2");
    }

    //=== 通用接口 ===//
    @GetMapping("/api/sensor/{type}")
    public List<SensorData> getByType(@PathVariable String type) {
        return repository.findTop20BySensorTypeOrderByTimestampDesc(type);
    }

    //=== 数据保存 ===//
    @PostMapping("/api/data")
    public ResponseEntity<?> saveData(@Valid @RequestBody SensorData data) {
        String sensorType = data.getSensorType();
        
        if (sensorType == null || sensorType.isBlank()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(
                400, "传感器类型不能为空", LocalDateTime.now()));
        }

        if (!VALID_SENSOR_TYPES.contains(sensorType.toLowerCase())) {
            return ResponseEntity.badRequest().body(new ErrorResponse(
                400, 
                "无效传感器类型，支持：" + VALID_SENSOR_TYPES,
                LocalDateTime.now()
            ));
        }

        data.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(repository.save(data));
    }
}