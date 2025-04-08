package br.com.spherams.infrastructure.dependency;


import br.com.spherams.adapter.output.JDBCCharacterRepository;
import br.com.spherams.adapter.output.JDBCFamilyRepository;
import br.com.spherams.application.service.FamilyService;
import br.com.spherams.domain.port.output.CharacterRepository;
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
            Injector.bind(CharacterRepository.class, new JDBCCharacterRepository());
            final FamilyRepository familyRepository = Injector.get(FamilyRepository.class);
            final CharacterRepository characterRepository = Injector.get(CharacterRepository.class);
            //Service
            final FamilyService familyService = new FamilyService(familyRepository, characterRepository);
            Injector.bind(FamilyService.class,familyService);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
}
