package epheluv.agri.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PlantingLane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer x;          // 种植道左上角X坐标
    private Integer y;          // 种植道左上角Y坐标
    private Integer width = 60; // 默认宽度
    private Integer height = 500; // 默认高度
}
