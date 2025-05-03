package epheluv.agri.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    @Autowired
    private KieContainer kieContainer;

    public void checkAlerts(SensorData data) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("alertService", this); // 绑定全局变量
        kieSession.insert(data); // 插入数据到规则引擎
        kieSession.fireAllRules(); // 触发规则
        kieSession.dispose();
    }

    @Autowired
    private AlertRepository alertRepository;

    // 创建告警记录
    public void createAlert(String zoneId, String sensorType, Double value) {
        Alert alert = new Alert();
        alert.setZoneId(zoneId);
        alert.setSensorType(sensorType);
        alert.setTriggerValue(value);
        alert.setTriggeredAt(LocalDateTime.now());
        alertRepository.save(alert);
    }

    // 标记告警为已解决
    public void resolveAlert(String zoneId, String sensorType) {
        List<Alert> activeAlerts = alertRepository.findByZoneIdAndSensorTypeAndStatus(
            zoneId, sensorType, "ACTIVE"
        );
        activeAlerts.forEach(alert -> {
            alert.setStatus("RESOLVED");
            alert.setResolvedAt(LocalDateTime.now());
        });
        alertRepository.saveAll(activeAlerts);
    }
}
