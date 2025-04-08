package br.com.spherams.application.service;

import br.com.spherams.client.Family;
import br.com.spherams.client.FamilyEntry;
import br.com.spherams.client.Job;
import br.com.spherams.domain.port.output.FamilyRepository;
import br.com.spherams.domain.port.output.CharacterRepository;
import br.com.spherams.infrastructure.persistence.model.CharactersEntity;
import br.com.spherams.infrastructure.persistence.model.FamilyCharacterEntity;
import br.com.spherams.infrastructure.persistence.model.FamilyEntitlementEntity;
import br.com.spherams.net.server.Server;
import br.com.spherams.net.server.world.World;
import br.com.spherams.tools.Pair;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FamilyService {

    private final FamilyRepository familyRepository;
    private final CharacterRepository characterRepository;

    public FamilyService(FamilyRepository familyRepository, CharacterRepository characterRepository) {
        this.familyRepository = familyRepository;
        this.characterRepository = characterRepository;
    }

    public void loadAllFamilies() {
        List<Pair<Pair<Integer, Integer>, FamilyEntry>> unmatchedJuniors = new ArrayList<>(200);
        List<FamilyCharacterEntity> familyRows = familyRepository.findAll();

        for (FamilyCharacterEntity row : familyRows) {
            loadFamilyCharacter(row, unmatchedJuniors);
        }

        processUnmatchedJuniors(unmatchedJuniors);
        finalizeFamilySetup();
    }

    private void loadFamilyCharacter(FamilyCharacterEntity row, List<Pair<Pair<Integer, Integer>, FamilyEntry>> unmatchedJuniors) {
        CharactersEntity character = characterRepository.findById(row.getCid()).orElse(null);
        if (character == null) throw new RuntimeException("Character is null");

        int worldId = (int) character.getWorld();
        World world = Server.getInstance().getWorld(worldId);
        if (world == null) return;

        int familyId = row.getFamilyId();
        Family family = world.getFamily(familyId);
        if (family == null) {
            family = new Family(familyId, worldId);
            world.addFamily(familyId, family);
        }

        FamilyEntry entry = new FamilyEntry(family, row.getCid(), character.getName(),
                (int) character.getLevel(), Job.getById((int) character.getJob()));

        populateFamilyEntry(entry, row);
        family.addEntry(entry);

        int seniorId = row.getSeniorId();
        if (seniorId <= 0) {
            family.setLeader(entry);
            family.setMessage(row.getPrecepts(), false);
        }

        FamilyEntry senior = family.getEntryByID(seniorId);
        if (senior != null) {
            entry.setSenior(senior, false);
        } else if (seniorId > 0) {
            unmatchedJuniors.add(new Pair<>(new Pair<>(worldId, seniorId), entry));
        }

        familyRepository.findFamilyEntitlementByCharacterId(row.getCid())
                .ifPresent(ent -> entry.setEntitlementUsed((int) ent.getEntitlementid()));
    }

    private void populateFamilyEntry(FamilyEntry entry, FamilyCharacterEntity row) {
        entry.setReputation(row.getReputation());
        entry.setTodaysRep(row.getTodaysrep());
        entry.setTotalReputation(row.getTotalreputation());
        entry.setRepsToSenior(row.getReptosenior());
    }

    private void processUnmatchedJuniors(List<Pair<Pair<Integer, Integer>, FamilyEntry>> unmatchedJuniors) {
        for (Pair<Pair<Integer, Integer>, FamilyEntry> pair : unmatchedJuniors) {
            int worldId = pair.getLeft().getLeft();
            int seniorId = pair.getLeft().getRight();
            FamilyEntry junior = pair.getRight();

            World world = Server.getInstance().getWorld(worldId);
            Family family = world.getFamily(junior.getFamily().getID());
            if (family == null) continue;

            FamilyEntry senior = family.getEntryByID(seniorId);
            if (senior != null) {
                junior.setSenior(senior, false);
            } else {
                // log.warn("Senior not found for {}", junior.getName());
            }
        }
    }

    private void finalizeFamilySetup() {
        for (World world : Server.getInstance().getWorlds()) {
            for (Family family : world.getFamilies()) {
                family.getLeader().doFullCount();
            }
        }
    }
}