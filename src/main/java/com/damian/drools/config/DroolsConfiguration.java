package com.damian.drools.config;

import lombok.extern.log4j.Log4j2;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Log4j2
public class DroolsConfiguration {
    private final KieServices kieServices = KieServices.Factory.get();

    private KieFileSystem getKieFileSystem() {
        log.info("DroolsConfiguration.getKieFileSystem invoked!");
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        //Below file should be located in the src/resources folder.
        kieFileSystem.write(ResourceFactory.newClassPathResource("businessRules.drl"));
        return kieFileSystem;

    }

    @Bean
    public KieContainer getKieContainer() {
        log.info("DroolsConfiguration.getKieContainer invoked!");
        getKieRepository();
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());

    }

    private void getKieRepository() {
        log.info("DroolsConfiguration.getKieRepository invoked!");
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
    }

    @Bean
    public KieSession getKieSession()  {
        log.info("DroolsConfiguration.getKieSession invoked!");
        return getKieContainer().newKieSession();
    }
}
