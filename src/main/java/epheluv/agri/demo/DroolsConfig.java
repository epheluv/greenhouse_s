package epheluv.agri.demo;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {
    private static final String RULES_PATH = "rules/sensor-alert.drl";
    private static final Logger logger = LoggerFactory.getLogger(DroolsConfig.class);

    @Bean
    public KieContainer kieContainer() {
        try {
            KieServices kieServices = KieServices.Factory.get();
            if (kieServices == null) {
                throw new IllegalStateException("KieServices 实例获取失败");
            }

            KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH));

            KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
            Results results = kieBuilder.getResults();
            if (results.hasMessages(Message.Level.ERROR)) {
                logger.error("规则编译错误: " + results.getMessages());
                throw new RuntimeException("规则文件存在错误");
            }

            KieModule kieModule = kieBuilder.getKieModule();
            return kieServices.newKieContainer(kieModule.getReleaseId());
        } catch (Exception e) {
            logger.error("Drools 配置失败", e);
            throw new RuntimeException("Drools 初始化异常", e);
        }
    }
}