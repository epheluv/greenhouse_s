package epheluv.agri.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/detection-points")
@RequiredArgsConstructor
public class DetectionPointController {
    private final DetectionPointRepository repository;

    @GetMapping
    public List<DetectionPoint> getAllPoints() {
        return repository.findAll();
    }
}
