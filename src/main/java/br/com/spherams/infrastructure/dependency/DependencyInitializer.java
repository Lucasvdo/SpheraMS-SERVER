package br.com.spherams.infrastructure.dependency;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DependencyInitializer {
    static final Logger logger = LoggerFactory.getLogger(DependencyInitializer.class);

    public static void initialize() {
        logger.info("Inicializando a injeção de dependencias!");

    }
}
