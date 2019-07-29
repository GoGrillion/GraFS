package com.gogrillion.grafs.db;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalDB extends GraFSDB {

    private JanusGraph graph;
    private GraphTraversalSource g;

    protected LocalDB(String dbFolderPath) throws IOException {

        if(!Files.exists(Paths.get(dbFolderPath))){
            Files.createDirectory(Paths.get(dbFolderPath));
        }

        graph = JanusGraphFactory.build().
                set("index.search.backend", "lucene").
                set("index.search.directory", dbFolderPath + File.pathSeparatorChar + "index").
                set("storage.backend", "berkeleyje").
                set("storage.directory", dbFolderPath + File.pathSeparatorChar + "db").
                open();
        g = graph.traversal();
    }

    public static boolean isLocalUri(String uri) {
        return uri.startsWith("local:");
    }

    public void close() throws Exception {
        try {
            if (this.g != null) {
                this.g.close();
            }
            if (this.graph != null) {
                this.graph.close();
            }
        } finally {
            this.g = null;
            this.graph = null;
        }
    }

}