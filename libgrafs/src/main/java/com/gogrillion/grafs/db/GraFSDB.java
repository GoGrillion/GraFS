package com.gogrillion.grafs.db;

import java.io.IOException;

public abstract class GraFSDB {

    public static GraFSDB openURI(String uri) throws UnknownUriSchemeException, IOException {
        if(LocalDB.isLocalUri(uri)) {
            return new LocalDB(uri);
        }
        throw new UnknownUriSchemeException();
    }

}
