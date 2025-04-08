package br.com.spherams;

import br.com.spherams.config.YamlConfig;
import br.com.spherams.constants.ServerApplicationConst;
import br.com.spherams.constants.net.ServerConstants;
import br.com.spherams.infrastructure.dependency.DependencyInitializer;
import br.com.spherams.net.ChannelDependencies;
import br.com.spherams.net.server.Server;
import br.com.spherams.tools.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class ServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);
    private static ChannelDependencies channelDependencies;

    public static void main(String[] args) {
        System.out.println(ServerApplicationConst.logoString);
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
        if(!DependencyInitializer.initialize()){
            throw new IllegalStateException("Failed to initialize the dependency dependency");
        };
        Server.getInstance().init();
    }
}
