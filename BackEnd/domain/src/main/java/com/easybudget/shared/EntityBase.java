package com.easybudget.shared;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityBase<T extends Serializable> extends Audit {

    @Version
    private Long version;

    public abstract T id();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityBase<?> that = (EntityBase<?>) o;
        return id().equals(that.id());
    }

    @Override
    public int hashCode() {
        if(id() != null){
            return id().hashCode();
        }
        return super.hashCode();
    }
}
