/*
 * This file is generated by jOOQ.
 */
package ilio.auth.core.model.generated.tables;


import ilio.auth.core.model.generated.AuthSys;
import ilio.auth.core.model.generated.Indexes;
import ilio.auth.core.model.generated.Keys;
import ilio.auth.core.model.generated.tables.records.AccessTokenRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccessToken extends TableImpl<AccessTokenRecord> {

    private static final long serialVersionUID = -1451605687;

    /**
     * The reference instance of <code>auth_sys.access_token</code>
     */
    public static final AccessToken ACCESS_TOKEN = new AccessToken();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccessTokenRecord> getRecordType() {
        return AccessTokenRecord.class;
    }

    /**
     * The column <code>auth_sys.access_token.id</code>.
     */
    public final TableField<AccessTokenRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>auth_sys.access_token.user_id</code>.
     */
    public final TableField<AccessTokenRecord, Long> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>auth_sys.access_token.token</code>.
     */
    public final TableField<AccessTokenRecord, String> TOKEN = createField("token", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>auth_sys.access_token.expires_at</code>.
     */
    public final TableField<AccessTokenRecord, LocalDateTime> EXPIRES_AT = createField("expires_at", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>auth_sys.access_token.created_time</code>.
     */
    public final TableField<AccessTokenRecord, LocalDateTime> CREATED_TIME = createField("created_time", org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>auth_sys.access_token.updated_time</code>.
     */
    public final TableField<AccessTokenRecord, LocalDateTime> UPDATED_TIME = createField("updated_time", org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>auth_sys.access_token</code> table reference
     */
    public AccessToken() {
        this(DSL.name("access_token"), null);
    }

    /**
     * Create an aliased <code>auth_sys.access_token</code> table reference
     */
    public AccessToken(String alias) {
        this(DSL.name(alias), ACCESS_TOKEN);
    }

    /**
     * Create an aliased <code>auth_sys.access_token</code> table reference
     */
    public AccessToken(Name alias) {
        this(alias, ACCESS_TOKEN);
    }

    private AccessToken(Name alias, Table<AccessTokenRecord> aliased) {
        this(alias, aliased, null);
    }

    private AccessToken(Name alias, Table<AccessTokenRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> AccessToken(Table<O> child, ForeignKey<O, AccessTokenRecord> key) {
        super(child, key, ACCESS_TOKEN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return AuthSys.AUTH_SYS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ACCESS_TOKEN_PRIMARY, Indexes.ACCESS_TOKEN_TOKEN, Indexes.ACCESS_TOKEN_USER_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AccessTokenRecord, Long> getIdentity() {
        return Keys.IDENTITY_ACCESS_TOKEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AccessTokenRecord> getPrimaryKey() {
        return Keys.KEY_ACCESS_TOKEN_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AccessTokenRecord>> getKeys() {
        return Arrays.<UniqueKey<AccessTokenRecord>>asList(Keys.KEY_ACCESS_TOKEN_PRIMARY, Keys.KEY_ACCESS_TOKEN_USER_ID, Keys.KEY_ACCESS_TOKEN_TOKEN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessToken as(String alias) {
        return new AccessToken(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessToken as(Name alias) {
        return new AccessToken(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AccessToken rename(String name) {
        return new AccessToken(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AccessToken rename(Name name) {
        return new AccessToken(name, null);
    }
}