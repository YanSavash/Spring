package ru.netrax.chandgelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

@ChangeLog(order = "001")
public class DropMongoDBDataChangeLog {

    @ChangeSet(order = "000", id = "dropDB", author = "netrax", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }
}
