package com.sessionmanager.mapper;

import com.sessionmanager.model.BaseEntity;

public interface GenericMapper<E extends BaseEntity, D> {

    E convertToEntity(D dto);
}