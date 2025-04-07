package br.com.spherams;

import br.com.spherams.constants.ServerApplicationConst;
import br.com.spherams.net.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);


    public static void main(String[] args) {
        System.out.println(ServerApplicationConst.logoString);
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
        Server.getInstance().init();
    }
}
