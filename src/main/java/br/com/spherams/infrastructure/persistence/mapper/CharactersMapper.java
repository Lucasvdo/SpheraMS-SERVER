package br.com.spherams.infrastructure.persistence.mapper;

import br.com.spherams.infrastructure.persistence.model.CharactersEntity;
import br.com.spherams.infrastructure.persistence.common.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CharactersMapper implements RowMapper<CharactersEntity> {
    public static final CharactersMapper INSTANCE = new CharactersMapper();

    private CharactersMapper() {

    }

    @Override
    public CharactersEntity map(ResultSet rs) throws SQLException {
        CharactersEntity character = new CharactersEntity();

        character.setId(rs.getLong("id"));
        character.setAccountid(rs.getLong("accountid"));
        character.setWorld(rs.getLong("world"));
        character.setName(rs.getString("name"));
        character.setLevel(rs.getLong("level"));
        character.setExp(rs.getLong("exp"));
        character.setGachaexp(rs.getLong("gachaexp"));
        character.setStr(rs.getLong("str"));
        character.setDex(rs.getLong("dex"));
        character.setLuk(rs.getLong("luk"));
        character.setInt_(rs.getLong("int")); // cuidado: "int" é palavra reservada em Java
        character.setHp(rs.getLong("hp"));
        character.setMp(rs.getLong("mp"));
        character.setMaxhp(rs.getLong("maxhp"));
        character.setMaxmp(rs.getLong("maxmp"));
        character.setMeso(rs.getLong("meso"));
        character.setHpMpUsed(rs.getLong("hpMpUsed"));
        character.setJob(rs.getLong("job"));
        character.setSkincolor(rs.getLong("skincolor"));
        character.setGender(rs.getLong("gender"));
        character.setFame(rs.getLong("fame"));
        character.setFquest(rs.getLong("fquest"));
        character.setHair(rs.getLong("hair"));
        character.setFace(rs.getLong("face"));
        character.setAp(rs.getLong("ap"));
        character.setSp(rs.getString("sp"));
        character.setMap(rs.getLong("map"));
        character.setSpawnpoint(rs.getLong("spawnpoint"));
        character.setGm(rs.getLong("gm"));
        character.setParty(rs.getLong("party"));
        character.setBuddyCapacity(rs.getLong("buddyCapacity"));
        character.setCreatedate(rs.getTimestamp("createdate"));
        character.setRank(rs.getLong("rank"));
        character.setRankMove(rs.getLong("rankMove"));
        character.setJobRank(rs.getLong("jobRank"));
        character.setJobRankMove(rs.getLong("jobRankMove"));
        character.setGuildid(rs.getLong("guildid"));
        character.setGuildrank(rs.getLong("guildrank"));
        character.setMessengerid(rs.getLong("messengerid"));
        character.setMessengerposition(rs.getLong("messengerposition"));
        character.setMountlevel(rs.getLong("mountlevel"));
        character.setMountexp(rs.getLong("mountexp"));
        character.setMounttiredness(rs.getLong("mounttiredness"));
        character.setOmokwins(rs.getLong("omokwins"));
        character.setOmoklosses(rs.getLong("omoklosses"));
        character.setOmokties(rs.getLong("omokties"));
        character.setMatchcardwins(rs.getLong("matchcardwins"));
        character.setMatchcardlosses(rs.getLong("matchcardlosses"));
        character.setMatchcardties(rs.getLong("matchcardties"));
        character.setMerchantMesos(rs.getLong("merchantMesos"));
        character.setHasMerchant(rs.getLong("hasMerchant"));
        character.setEquipslots(rs.getLong("equipslots"));
        character.setUseslots(rs.getLong("useslots"));
        character.setSetupslots(rs.getLong("setupslots"));
        character.setEtcslots(rs.getLong("etcslots"));
        character.setFamilyId(rs.getLong("familyId"));
        character.setMonsterbookcover(rs.getLong("monsterbookcover"));
        character.setAllianceRank(rs.getLong("allianceRank"));
        character.setVanquisherStage(rs.getLong("vanquisherStage"));
        character.setAriantPoints(rs.getLong("ariantPoints"));
        character.setDojoPoints(rs.getLong("dojoPoints"));
        character.setLastDojoStage(rs.getLong("lastDojoStage"));
        character.setFinishedDojoTutorial(rs.getLong("finishedDojoTutorial"));
        character.setVanquisherKills(rs.getLong("vanquisherKills"));
        character.setSummonValue(rs.getLong("summonValue"));
        character.setPartnerId(rs.getLong("partnerId"));
        character.setMarriageItemId(rs.getLong("marriageItemId"));
        character.setReborns(rs.getLong("reborns"));
        character.setPqPoints(rs.getLong("pqPoints"));
        character.setDataString(rs.getString("dataString"));
        character.setLastLogoutTime(rs.getTimestamp("lastLogoutTime"));
        character.setLastExpGainTime(rs.getTimestamp("lastExpGainTime"));
        character.setPartySearch(rs.getLong("partySearch"));
        character.setJailexpire(rs.getLong("jailexpire"));
        return character;
    }
}
