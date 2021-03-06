/*
 * This file is generated by jOOQ.
 */
package ilio.auth.core.model.generated;


import ilio.auth.core.model.generated.tables.FlywaySchemaHistory;
import ilio.auth.core.model.generated.tables.User;
import ilio.auth.core.model.generated.tables.records.FlywaySchemaHistoryRecord;
import ilio.auth.core.model.generated.tables.records.UserRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>auth_sys</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<UserRecord, Long> IDENTITY_USER = Identities0.IDENTITY_USER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = UniqueKeys0.KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY;
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;
    public static final UniqueKey<UserRecord> KEY_USER_USER_ID = UniqueKeys0.KEY_USER_USER_ID;
    public static final UniqueKey<UserRecord> KEY_USER_USERNAME = UniqueKeys0.KEY_USER_USERNAME;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<UserRecord, Long> IDENTITY_USER = Internal.createIdentity(User.USER, User.USER.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "KEY_flyway_schema_history_PRIMARY", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = Internal.createUniqueKey(User.USER, "KEY_user_PRIMARY", User.USER.ID);
        public static final UniqueKey<UserRecord> KEY_USER_USER_ID = Internal.createUniqueKey(User.USER, "KEY_user_user_id", User.USER.USER_ID);
        public static final UniqueKey<UserRecord> KEY_USER_USERNAME = Internal.createUniqueKey(User.USER, "KEY_user_username", User.USER.USERNAME);
    }
}
