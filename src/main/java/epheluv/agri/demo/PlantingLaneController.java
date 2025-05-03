package epheluv.agri.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/planting-lanes")
@RequiredArgsConstructor
public class PlantingLaneController {
    private final PlantingLaneRepository repository;

    @GetMapping
    public List<PlantingLane> getAllLanes() {
        return repository.findAll();
    }
}
