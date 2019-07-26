package com.gogrillion.grafs;

import picocli.CommandLine;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

/**
 * Index a folder
 * grafs {dbUri} --index {absolute_path}
 */
@CommandLine.Command(name = "grafs")
public class GraFSCli implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Database URI") String dbUri;
    @CommandLine.Option()

    public Integer call() throws Exception {

        System.out.printf("Database URI: %s\n", dbUri);

        System.out.printf("Action path: %s\n", actionPath);

        Path dir = Paths.get(this.actionPath);

        crawlDir(dir, 0);

        return 0;
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
        int exitCode = new CommandLine(new GraFSCli()).execute(args);
        System.exit(exitCode);
    }
}

