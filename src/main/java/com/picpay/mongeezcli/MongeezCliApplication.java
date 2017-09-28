package com.picpay.mongeezcli;

import com.mongodb.Mongo;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.mongeez.Mongeez;
import org.mongeez.MongoAuth;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

public class MongeezCliApplication {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (String arg : args) {
            String[] params = arg.split("=");
            if ("-help".equals(params[0])) {
                System.out.println("Mongeez Client Tool v0.0.1");
                System.out.println("Options:");
                System.out.println("-path: Location of the migration files.");
                System.out.println("-host: Mongo host.");
                System.out.println("-port: Mongo port.");
                System.out.println("-port: Database name.");
                System.out.println("-user: Authentication user.");
                System.out.println("-password: Authentication password.");
                return;
            }
            if (params.length >= 2) {
                map.put(params[0], params[1]);
            } else {
                map.put(arg, arg);
            }
        }
        String _path = map.get("-path");
        String path = StringUtils.isEmpty(_path) ? "." : _path;

        String _host = map.get("-host");
        String host = StringUtils.isEmpty(_host) ? "localhost" : _host;

        String _port = map.get("-port");
        Integer port = StringUtils.isEmpty(_port) ? 27017 : Integer.valueOf(_port);

        String _dbName = map.get("-db");
        String dbName = StringUtils.isEmpty(_dbName) ? "default" : _dbName;

        Mongeez mongeez = new Mongeez();
        mongeez.setFile(getFiles(path));
        mongeez.setMongo(new Mongo(host, port));
        mongeez.setDbName(dbName);

        String user = map.get("-user");
        if (!StringUtils.isEmpty(user)) {
            String _password = map.get("-password");
            String password = StringUtils.isEmpty(_password) ? "" : _password;
            MongoAuth auth = new MongoAuth(user, password, dbName);
            mongeez.setAuth(auth);
        }
        mongeez.process();
    }

    private static Resource getFiles(String path) {
        StringBuilder xml = new StringBuilder();
        xml.append("<changeFiles>");
        File folder = new File(path);
        Pattern p = Pattern.compile(".*\\.js|.*\\.xml");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getAbsolutePath().replace("./", "");
                if (p.matcher(fileName).find()) {
                    xml.append(String.format("<file path=\"%s\"/>", fileName));
                }
            }
        }
        xml.append("</changeFiles>");
        return new CustomResource(xml.toString().getBytes());
    }
}
