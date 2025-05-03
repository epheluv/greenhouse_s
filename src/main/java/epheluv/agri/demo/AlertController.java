package epheluv.agri.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {
    private final AlertRepository alertRepository;

    // 获取未解决的告警
    @GetMapping("/active")
    public List<Alert> getActiveAlerts() {
        return alertRepository.findByStatus("ACTIVE");
    }

    // 获取所有告警历史
    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    // AlertController.java
    @GetMapping("/recent")
    public Map<String, Long> getRecentStats() {
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        return Map.of(
                "resolved", alertRepository.countByStatusAndTriggeredAtAfter("RESOLVED", start),
                "unresolved", alertRepository.countByStatusAndTriggeredAtAfter("ACTIVE", start));
    }

    @GetMapping("/recent/list")
    public List<Alert> getRecentAlerts() {
        return alertRepository.findByTriggeredAtAfter(
                LocalDateTime.now().minusDays(7),
                Sort.by(Sort.Direction.DESC, "triggeredAt"));
    }

}
