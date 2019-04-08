package ilio.auth.core.model.support;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {

    @Autowired
    private DSLContext dsl;

    protected DSLContext dsl() {
        return this.dsl;
    }

}
