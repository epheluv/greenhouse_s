# Greenhouse
For homework of Smart Agri.

---

This assignment is due tomorrow(2025/5/5). Everything I have done so far is already my limit. In the future, I may not make any further modifications personally. However, I am very willing to offer this insignificant help to those in need.  

After much thought, my knowledge in agriculture was as shallow as that in computer science. I accomplished all this merely by virtue of my "excellent web search skills". At this stage of the project, I don't quite remember how many of the early things were implemented.

---
# TODO List
Project function realization

Core framework and basic functions
- [x] Back-end framework: Complete integration of Spring Boot (entity classes, repository interfaces, controllers)
- [x] Front-end framework: Component-based development is implemented using Vue 3 + Pinia
- [x] Data persistence: The storage of sensor data, planting paths, and inspection routes is accomplished through JPA + MySQL
- [x] Global exception handling: 'GlobalExceptionHandler' uniformly returns error responses
- [x] CORS configuration: Supports front-end cross-domain requests (' CorsConfig.java ')

Internet of Things communication
- [] MQTT integration: Currently, only HTTP is supported. Eclipse Paho needs to be added to implement device data subscription/publication
- [] Hardware integration: Arduino/ Raspberry PI control logic implementation

Sensor monitoring system
- [x] data collection: provide sensor data to interface (` SensorController. SaveSensorData `)
- [x] Data Query: Supports querying the latest data by region/type (custom query of 'SensorRepository')
- [x] Data Visualization
Heatmap (' SensorHeatmap.vue ')
- Line chart (' ZoneLineChart.vue ')
- [x] Automatic alert: Integrate the Drools rule engine to implement threshold alert (' sensor-alert.drl ')

Data storage optimization
- [] Time series database: MySQL is still used to store sensor data and needs to be migrated to InfluxDB
Cold and hot data separation: The historical data archiving strategy has not been designed

Lesion inspection system
- [x] SVG Visualization: Dynamic rendering of planting paths, inspection paths, and inspection points (' DiseaseInspection.vue ')
- [x] Data interaction: Load data through interfaces such as' /api/planting-lanes' and '/api/inspection-paths'
- [x] Interactive functions: Hover prompt for detection points, dynamic path generation (' generatePathD ')

- Equipment control logic
- [x] AirConditioningSystem: The 'AirConditioning system' starts and stops based on temperature thresholds
- [x] DehumidificationSystem: The 'dehumidification system' is controlled based on the humidity threshold
- [x] VentilationSystem: 'Ventilation system' is managed based on CO₂ concentration

"Advanced functions"
- [] Machine learning Prediction: Integration of TensorFlow/PyTorch models (Requires cross-language invocation from Python to Java)
[] Rule Engine Extension: Drools only implements basic alerts and lacks a dynamic rule update interface
Message queue: Asynchronous tasks (such as batch data processing) do not use Kafka/RabbitMQ

- Security and Deployment
- [] Authentication and Authorization: The interface has no permission control (Spring Security + JWT needs to be integrated)
- [] Containerization: Lack of Dockerfile and Kubernetes deployment configuration
- [] Cloud service integration: Not connected to AWS IoT/Aliyun IoT platform

"Front-end extension"
- [] Mobile end: Only implemented on the Web end, requiring the development of Android/Flutter applications
- [] Multilingual support: Unconfigured internationalization (i18n)
3D visualization: Complex spatial data still needs to be supplemented by Three.js

---

Code optimization
Front-end performance
[] Heat map and line graph components add anti-shake loading (to avoid frequent refreshing)
Use WebSocket instead of polling to achieve real-time updates
Back-end scalability
Abstract the device control logic into a unified interface (such as' DeviceControlService ')
- [] Add Swagger interface documentation
Data consistency
- [] Supplement data validation annotations for 'SensorData' (such as' @Min '/' @Max ')
- [] Add database transaction management (' @Transactional ')