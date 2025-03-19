package epheluv.agri.demo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data // Lombok自动生成getter/setter
public class SensorData {
    @Id
    @GeneratedValue
    private Long id;
    private String sensorType; // temperature/humidity
    private Double value;
    private LocalDateTime timestamp;
}
