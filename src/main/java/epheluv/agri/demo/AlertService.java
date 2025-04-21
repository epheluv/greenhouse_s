package epheluv.agri.demo;

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
        kieSession.insert(data); // 插入数据到规则引擎
        kieSession.fireAllRules(); // 触发规则
        kieSession.dispose();
    }
}
