package br.com.spherams.application.service;

import br.com.spherams.domain.port.output.FamilyRepository;

public class FamilyService {

    private FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }


}
