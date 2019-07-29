package com.gogrillion.grafs;

import com.gogrillion.grafs.db.GraFSDB;
import com.gogrillion.grafs.db.UnknownUriSchemeException;
import picocli.CommandLine;
import java.util.concurrent.Callable;

/**
 * Index a folder
 * grafs {dbUri} --index {absolute_path}
 */
@CommandLine.Command(name = "grafs")
public class GraFSCli implements Callable<Integer> {

    private @CommandLine.Parameters(index = "0", description = "Database URI") String dbUri;

    public Integer call() throws Exception {
        System.out.printf("Database URI: %s\n", this.dbUri);

        try {
            GraFSDB db = GraFSDB.openURI(this.dbUri);
        } catch (UnknownUriSchemeException e) {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new GraFSCli()).execute(args);
        System.exit(exitCode);
    }
}

