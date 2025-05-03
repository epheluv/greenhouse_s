package epheluv.agri.demo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class InspectionPath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 路径名称（可选）
    
    @OneToMany(mappedBy = "path", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PathPoint> points = new ArrayList<>(); // 必须初始化集合
}


