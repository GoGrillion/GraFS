package com.gogrillion.grafs;

import com.gogrillion.grafs.db.LocalDB;
import picocli.CommandLine;
import java.util.concurrent.Callable;

/**
 * Example:
 * user@localhost#] grafs /path/to/index
 *
 *
 */
@CommandLine.Command(name = "grafs-server")
public class GraFSServer implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Database directory") String dbPath;

    private LocalDB grafsDb;

    public Integer call() throws Exception {

        System.out.printf("Database path: %s\n", dbPath);
        try {
            this.grafsDb = new LocalDB(dbPath);
        } catch (Exception e){
            return 1;
        }
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new GraFSServer()).execute(args);
        System.exit(exitCode);
    }
}
