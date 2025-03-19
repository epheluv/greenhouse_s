package epheluv.agri.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // Lombok自动注入
public class SensorController {
    private final SensorRepository repository;

    // 保存传感器数据
    @PostMapping("/api/data")
    public SensorData saveData(@RequestBody SensorData data) {
        data.setTimestamp(LocalDateTime.now());
        return repository.save(data);
    }

    // 获取最新10条数据
    @GetMapping("/api/temperature")
    public List<SensorData> getTemperature() {
        return repository.findTop10BySensorTypeOrderByTimestampDesc("temperature");
    }
}
