package com.gogrillion.grafs;

import org.janusgraph.core.JanusGraphFactory;
import picocli.CommandLine;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
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

    private GraFSDB grafsDb;

    public Integer call() throws Exception {

        System.out.printf("Database path: %s\n", dbPath);
        this.g = JanusGraphFactory.build().
                set("storage.backend", "berkeleyje").
                set("storage.directory", dbPath).
                open();

    }

    private void crawlDir(Path dir, Integer depth) throws Exception {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry: stream) {
                if(Files.isDirectory(entry)){
                    crawlDir(entry, depth + 1);
                }
                System.out.println(entry.toString());
            }
        }
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new GraFSServer()).execute(args);
        System.exit(exitCode);
    }
}
