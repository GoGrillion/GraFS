package com.gogrillion.grafs;

import com.gogrillion.grafs.db.GraFSDB;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class IndexAction extends Action {

    private String indexPath;

    IndexAction(GraFSDB dbInst, String indexPath){
        super(dbInst);
        this.indexPath = indexPath;
    }

    @Override
    Integer run() throws ActionException {
        System.out.println("IndexAction.run()] Indexing Path: " + this.indexPath );
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

}
