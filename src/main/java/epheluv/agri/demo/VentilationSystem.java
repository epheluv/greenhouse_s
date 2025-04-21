package epheluv.agri.demo;

import java.util.HashSet;
import java.util.Set;

public class VentilationSystem {

    // 存储已启动通风的区域
    private static final Set<String> activeZones = new HashSet<>();

    public static void activateVentilation(SensorData data) {
        String zoneId = data.getZoneId();
        double value = data.getValue();
        if (!activeZones.contains(zoneId)) {
            activeZones.add(zoneId);
            // 这里可以添加实际的通风系统控制逻辑
            System.out.println("二氧化碳浓度过高，区域：" + zoneId + " 浓度：" + value);
            System.out.println("通风系统已开启，区域：" + zoneId);
            // 调用通风系统 API 或发送控制信号
        }
    }

    public static void deactivateVentilation(SensorData data) {
        String zoneId = data.getZoneId();
        if (activeZones.contains(zoneId)) {
            activeZones.remove(zoneId);
            // 这里可以添加实际的通风系统控制逻辑
            System.out.println("通风系统已停止，区域：" + zoneId);
            // 调用通风系统 API 或发送控制信号
        }
    }
}