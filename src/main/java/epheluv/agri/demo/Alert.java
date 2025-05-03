package epheluv.agri.demo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zoneId;
    private String sensorType;
    private String message;
    private Double triggerValue;
    private String status = "ACTIVE";  // 默认状态为 ACTIVE
    private LocalDateTime triggeredAt;
    private LocalDateTime resolvedAt;
}
