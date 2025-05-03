package epheluv.agri.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class PathPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer x;  // 路径点X坐标
    private Integer y;  // 路径点Y坐标
    private Integer orderIndex; // 点的顺序
    
    @ManyToOne
    @JoinColumn(name = "path_id")
    @JsonIgnore // 禁止序列化反向引用
    private InspectionPath path;
}

@Data
@AllArgsConstructor
class PathPointDTO {
    private Long id;
    private Integer x;
    private Integer y;
    private Integer orderIndex;
}
