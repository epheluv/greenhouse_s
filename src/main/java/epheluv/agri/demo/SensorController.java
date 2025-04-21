package epheluv.agri.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorRepository repository;
    private final AlertService alertService;
    
    // 区域数据写入接口
    @PostMapping
    public ResponseEntity<?> saveSensorData(@Valid @RequestBody SensorData data) {
        data.setTimestamp(LocalDateTime.now());
        SensorData savedData = repository.save(data);
        
        // 触发告警规则检查
        alertService.checkAlerts(savedData);
        
        return ResponseEntity.ok(savedData);
    }

    // 多维度数据查询接口
    @GetMapping("/{type}")
    public ResponseEntity<?> getSensorData(
        @PathVariable String type,
        @RequestParam(required = false) String zone,
        @RequestParam(defaultValue = "20") int limit
    ) {
        if (zone != null) {
            // 单区域查询
            Pageable pageable = PageRequest.of(0, limit);
            return ResponseEntity.ok(
                repository.findLatestByZone(type, zone, pageable)
            );
        } else {
            // 全区域聚合
            return ResponseEntity.ok(repository.findLatestAllZones(type));
        }
    }

    // 数据分析接口
    @GetMapping("/{type}/analytics")
    public List<TrendDTO> getTrendAnalysis(
        @PathVariable String type,
        @RequestParam @Past @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start
) {
        return repository.analyzeHistoricalTrend(type, start).stream()
        .map(result -> new TrendDTO(
            result[0] != null ? (LocalDateTime) result[0] : LocalDateTime.MIN,
            result[1] != null ? (Double) result[1] : 0.0,
            result[2] != null ? (String) result[2] : "Unknown"
        ))
            .collect(Collectors.toList());
    }
    
    // DTO类
    @Data
    @AllArgsConstructor
    static class TrendDTO {
        private LocalDateTime time;
        private Double average;
        private String zoneId;
    }
}