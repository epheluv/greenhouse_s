package epheluv.agri.demo;

import java.util.HashSet;
import java.util.Set;

public class DehumidificationSystem {

    // 存储已启动除湿的区域
    private static final Set<String> activeZones = new HashSet<>();

    public static void activateDehumidification(SensorData data) {
        String zoneId = data.getZoneId();
        double value = data.getValue();
        if (!activeZones.contains(zoneId)) {
            activeZones.add(zoneId);
            // 这里可以添加实际的除湿系统控制逻辑
            System.out.println("湿度过高，区域：" + zoneId + " 湿度：" + value);
            System.out.println("除湿系统已开启，区域：" + zoneId);
            // 调用除湿系统 API 或发送控制信号
        }
    }

    public static void deactivateDehumidification(SensorData data) {
        String zoneId = data.getZoneId();
        if (activeZones.contains(zoneId)) {
            activeZones.remove(zoneId);
            // 这里可以添加实际的除湿系统控制逻辑
            System.out.println("除湿系统已停止，区域：" + zoneId);
            // 调用除湿系统 API 或发送控制信号
        }
    }
}