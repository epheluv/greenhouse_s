package epheluv.agri.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inspection-paths")
@RequiredArgsConstructor
public class InspectionPathController {
    private final InspectionPathRepository pathRepository;

    @GetMapping
    public ResponseEntity<?> getAllPaths() {
        List<InspectionPath> paths = pathRepository.findAllWithPoints();
        
        // 转换为 DTO 避免循环引用
        List<PathDTO> dtos = paths.stream()
            .map(p -> new PathDTO(
                p.getId(),
                p.getName(),
                p.getPoints().stream()
                    .map(point -> new PointDTO(point.getX(), point.getY()))
                    .collect(Collectors.toList())
            ))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }

    // 定义 DTO 类
    @Data
    @AllArgsConstructor
    private static class PathDTO {
        private Long id;
        private String name;
        private List<PointDTO> points;
    }

    @Data
    @AllArgsConstructor
    private static class PointDTO {
        private Integer x;
        private Integer y;
    }
}
