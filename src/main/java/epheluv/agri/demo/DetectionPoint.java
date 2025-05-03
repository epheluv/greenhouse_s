package epheluv.agri.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DetectionPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cx;         // 圆心X坐标
    private Integer cy;         // 圆心Y坐标
    private String position;    // 位置描述（如"A区-2号道"）
    private String disease;     // 病症类型（如"叶霉病"）
    private Double confidence;  // 置信度（如85.0）
}
