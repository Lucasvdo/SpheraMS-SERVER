package br.com.spherams.infrastructure.dependency;


import br.com.spherams.adapter.output.JDBCFamilyRepository;
import br.com.spherams.application.service.FamilyService;
import br.com.spherams.domain.port.output.FamilyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DependencyInitializer {
    static final Logger logger = LoggerFactory.getLogger(DependencyInitializer.class);

    public static boolean initialize() {
        logger.info("Inicializando a injeção de dependencias!");
        try{
            //Repository
            Injector.bind(FamilyRepository.class, new JDBCFamilyRepository());
            //Service
            Injector.bind(FamilyService.class, new FamilyService(Injector.get(FamilyRepository.class)));
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
}
