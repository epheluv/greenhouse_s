package epheluv.agri.demo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Zone {
    @Id //区域ID：A1/B2等
    private String id;       // 区域ID：A1/B2等
    private String name;     // 区域名称：东南角/育苗区
    private Double coordX;   // 坐标X（相对位置）
    private Double coordY;   // 坐标Y
    private String type;     // 区域类型：种植区/设备区
}
