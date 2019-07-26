package com.gogrillion.grafs;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;
import org.apache.commons.configuration.Configuration;

import java.io.File;

public class GraFSDB {

    private final ;
    private Graph graph;
    private GraphTraversalSource g;

    static GraFSDB openDatabase(String dbFolderPath){
        return new GraFSDB(dbFolderPath);
    }

    protected GraFSDB(String dbFolderPath){
        PropertiesConfiguration conf = new PropertiesConfiguration();
        conf.addProperty("storage.backend", "berkeleyje");
        conf.addProperty("storage.directory", dbFolderPath + File.pathSeparatorChar + "db");
        conf.addProperty("index.search.backend", "lucene");
        conf.addProperty("index.search.directory", dbFolderPath + File.pathSeparatorChar + "index");
        graph = GraphFactory.open(conf);
        g = graph.traversal();
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