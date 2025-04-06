package br.com.spherams.client.command.commands.gm6;

import br.com.spherams.client.Client;
import br.com.spherams.client.command.Command;
import br.com.spherams.scripting.AbstractScriptManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class DevtestCommand extends Command {
    {
        setDescription("Runs devtest.js. Developer utility - test stuff without restarting the server.");
    }

    private static final Logger log = LoggerFactory.getLogger(DevtestCommand.class);

    private static class DevtestScriptManager extends AbstractScriptManager {

        @Override
        public ScriptEngine getInvocableScriptEngine(String path) {
            return super.getInvocableScriptEngine(path);
        }

    }

    @Override
    public void execute(Client client, String[] params) {
        DevtestScriptManager scriptManager = new DevtestScriptManager();
        ScriptEngine scriptEngine = scriptManager.getInvocableScriptEngine("devtest.js");
        try {
            Invocable invocable = (Invocable) scriptEngine;
            invocable.invokeFunction("run", client.getPlayer());
        } catch (ScriptException | NoSuchMethodException e) {
            log.info("devtest.js run() threw an exception", e);
        }
    }
}
