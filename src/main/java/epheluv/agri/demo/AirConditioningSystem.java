package epheluv.agri.demo;

import java.util.HashSet;
import java.util.Set;

public class AirConditioningSystem {

    // 存储已启动空调的区域
    private static final Set<String> activeZones = new HashSet<>();

    public static void activateCooling(SensorData data) {
        String zoneId = data.getZoneId();
        double value = data.getValue();
        if (!activeZones.contains(zoneId)) {
            activeZones.add(zoneId);
            // 这里可以添加实际的空调系统控制逻辑
            System.out.println("温度过高，区域："+zoneId+" 温度："+value);
            System.out.println("空调已开启制冷模式，区域：" + zoneId);
            // 调用空调系统 API 或发送控制信号
        }
    }

    public static void deactivateCooling(SensorData data) {
        String zoneId = data.getZoneId();
        if (activeZones.contains(zoneId)) {
            activeZones.remove(zoneId);
            // 这里可以添加实际的空调系统控制逻辑
            System.out.println("空调已停止制冷模式，区域：" + zoneId);
            // 调用空调系统 API 或发送控制信号
        }
    }
}
