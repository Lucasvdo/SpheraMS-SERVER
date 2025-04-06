package br.com.spherams.server.life;

import br.com.spherams.client.Character;

public interface MonsterListener {

    void monsterKilled(int aniTime);
    void monsterDamaged(Character from, int trueDmg);
    void monsterHealed(int trueHeal);
}
