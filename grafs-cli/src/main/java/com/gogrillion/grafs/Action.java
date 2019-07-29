package com.gogrillion.grafs;

import com.gogrillion.grafs.db.GraFSDB;

public abstract class Action {

    protected final GraFSDB dbInstance;

    Action(GraFSDB dbInstance){
        this.dbInstance = dbInstance;
    }

    abstract Integer run() throws ActionException;

}
