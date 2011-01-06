/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.tricreo.ddd.base.lifecycle.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.tricreo.ddd.base.lifecycle.Repository;
import jp.tricreo.ddd.base.lifecycle.exception.EntityMultipleFoundRuntimeException;
import jp.tricreo.ddd.base.lifecycle.exception.EntityNotFoundRuntimeException;
import jp.tricreo.ddd.base.model.Entity;
import jp.tricreo.ddd.base.model.EntityIdentifier;

import com.google.common.base.Predicate;

import org.apache.commons.lang.Validate;

/**
 * オンメモリ実装のリポジトリ。
 *
 * @param <T> エンティティの型
 * @author j5ik2o
 */
public class OnMemoryRepository<T extends Entity<T>> implements Repository<T>, Cloneable {
	
	private final Map<EntityIdentifier<T>, T> entities = new HashMap<EntityIdentifier<T>, T>();
	

	@Override
	public List<T> asEntitiesList() {
		List<T> result = new ArrayList<T>(entities.size());
		for (T entity : entities.values()) {
			result.add(entity.clone());
		}
		return result;
	}
	
	@Override
	public Set<T> asEntitiesSet() {
		Set<T> result = new HashSet<T>(entities.size());
		for (T entity : entities.values()) {
			result.add(entity.clone());
		}
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public OnMemoryRepository<T> clone() {
		try {
			return (OnMemoryRepository<T>) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("clone not supported");
		}
	}
	
	@Override
	public boolean contains(EntityIdentifier<T> identifier) {
		Validate.notNull(identifier);
		return entities.containsKey(identifier);
	}
	
	@Override
	public boolean contains(Predicate<T> predicate) {
		Validate.notNull(predicate);
		for (T entity : entities.values()) {
			if (predicate.apply(entity)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean contains(T entity) {
		Validate.notNull(entity);
		return contains(entity.getIdentifier());
	}
	
	@Override
	public void delete(EntityIdentifier<T> identifier) {
		Validate.notNull(identifier);
		entities.remove(identifier);
	}
	
	@Override
	public void delete(T entity) {
		Validate.notNull(entity);
		delete(entity.getIdentifier());
	}
	
	@Override
	public T resolve(EntityIdentifier<T> identifier) {
		Validate.notNull(identifier);
		if (contains(identifier) == false) {
			throw new EntityNotFoundRuntimeException();
		}
		return entities.get(identifier).clone();
	}
	
	@Override
	public T resolve(Predicate<T> predicate) {
		Validate.notNull(predicate);
		T result = null;
		for (T entity : entities.values()) {
			if (predicate.apply(entity)) {
				if (result != null) {
					throw new EntityMultipleFoundRuntimeException();
				}
				result = entity;
			}
		}
		if (result == null) {
			throw new EntityNotFoundRuntimeException();
		}
		return result;
	}
	
	@Override
	public void store(T entity) {
		Validate.notNull(entity);
		entities.put(entity.getIdentifier(), entity.clone());
	}
}
