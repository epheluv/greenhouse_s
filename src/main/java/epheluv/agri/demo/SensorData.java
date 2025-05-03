package epheluv.agri.demo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // Lombok自动生成getter/setter
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 传感器基础信息
    private String sensorType; // 监测类型：temperature/humidity/co2/pressure等
    private Double value; // 传感器数值
    private LocalDateTime timestamp; // 采集时间

    // 新增区域标识系统
    private String zoneId; // 区域唯一标识（如：A1/B3）
    private String sector; // 物理分区（如：North/West）

    // 动态元数据（可选）
    @Column(columnDefinition = "JSON")
    private String metadata; // 存储传感器位置等扩展信息
}
