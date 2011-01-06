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
package jp.tricreo.ddd.base.model.impl;

import jp.tricreo.ddd.base.model.Entity;
import jp.tricreo.ddd.base.model.EntityIdentifier;

import org.apache.commons.lang.Validate;

/**
 * {@link jp.tricreo.ddd.base.model.Entity}の骨格実装。
 *
 * @param <T> エンティティの型 
 * @author j5ik2o
 */
public abstract class AbstractEntity<T extends Entity<T>> implements Entity<T> {
	
	private final EntityIdentifier<T> identifier;
	

	/**
	 * インスタンスを生成する。
	 *
	 * @param identifier {@link EntityIdentifier}
	 */
	protected AbstractEntity(EntityIdentifier<T> identifier) {
		Validate.notNull(identifier);
		this.identifier = identifier;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T clone() {
		try {
			return (T) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("clone not supported");
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null || that instanceof Entity == false) {
			return false;
		}
		return identifier.equals(((T) that).getIdentifier());
	}
	
	@Override
	public EntityIdentifier<T> getIdentifier() {
		return identifier;
	}
	
	@Override
	public int hashCode() {
		return identifier.hashCode();
	}
}
