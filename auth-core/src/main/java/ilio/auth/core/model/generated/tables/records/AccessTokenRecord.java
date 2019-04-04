/*
 * This file is generated by jOOQ.
 */
package ilio.auth.core.model.generated.tables.records;


import ilio.auth.core.model.generated.tables.AccessToken;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


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
public class AccessTokenRecord extends UpdatableRecordImpl<AccessTokenRecord> implements Record6<Long, Long, String, LocalDateTime, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = -1876243848;

    /**
     * Setter for <code>auth_sys.access_token.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>auth_sys.access_token.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>auth_sys.access_token.user_id</code>.
     */
    public void setUserId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>auth_sys.access_token.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>auth_sys.access_token.token</code>.
     */
    public void setToken(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>auth_sys.access_token.token</code>.
     */
    public String getToken() {
        return (String) get(2);
    }

    /**
     * Setter for <code>auth_sys.access_token.expires_at</code>.
     */
    public void setExpiresAt(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>auth_sys.access_token.expires_at</code>.
     */
    public LocalDateTime getExpiresAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>auth_sys.access_token.created_time</code>.
     */
    public void setCreatedTime(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>auth_sys.access_token.created_time</code>.
     */
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>auth_sys.access_token.updated_time</code>.
     */
    public void setUpdatedTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>auth_sys.access_token.updated_time</code>.
     */
    public LocalDateTime getUpdatedTime() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Long, Long, String, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Long, Long, String, LocalDateTime, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return AccessToken.ACCESS_TOKEN.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return AccessToken.ACCESS_TOKEN.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return AccessToken.ACCESS_TOKEN.TOKEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field4() {
        return AccessToken.ACCESS_TOKEN.EXPIRES_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field5() {
        return AccessToken.ACCESS_TOKEN.CREATED_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field6() {
        return AccessToken.ACCESS_TOKEN.UPDATED_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getToken();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component4() {
        return getExpiresAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component5() {
        return getCreatedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component6() {
        return getUpdatedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getToken();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value4() {
        return getExpiresAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value5() {
        return getCreatedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value6() {
        return getUpdatedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRecord value2(Long value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRecord value3(String value) {
        setToken(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRecord value4(LocalDateTime value) {
        setExpiresAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRecord value5(LocalDateTime value) {
        setCreatedTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRecord value6(LocalDateTime value) {
        setUpdatedTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRecord values(Long value1, Long value2, String value3, LocalDateTime value4, LocalDateTime value5, LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccessTokenRecord
     */
    public AccessTokenRecord() {
        super(AccessToken.ACCESS_TOKEN);
    }

    /**
     * Create a detached, initialised AccessTokenRecord
     */
    public AccessTokenRecord(Long id, Long userId, String token, LocalDateTime expiresAt, LocalDateTime createdTime, LocalDateTime updatedTime) {
        super(AccessToken.ACCESS_TOKEN);

        set(0, id);
        set(1, userId);
        set(2, token);
        set(3, expiresAt);
        set(4, createdTime);
        set(5, updatedTime);
    }
}