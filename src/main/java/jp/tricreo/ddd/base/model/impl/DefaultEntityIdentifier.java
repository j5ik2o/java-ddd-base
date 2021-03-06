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

import java.util.UUID;

import jp.tricreo.ddd.base.model.Identifier;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * {@link Identifier}のデフォルト実装。
 *
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class DefaultEntityIdentifier implements Identifier {
	
	/**
	 * ファクトリメソッド。
	 * 
	 * @param entityClass エンティティクラス
	 * @param uuid {@link UUID}
	 * @return {@link DefaultEntityIdentifier}
	 */
	public static DefaultEntityIdentifier of(Class<?> entityClass, UUID uuid) {
		return new DefaultEntityIdentifier(entityClass, uuid);
	}
	

	private String kind;
	
	private UUID uuid;
	

	/**
	 * インスタンスを生成する。
	 * <p>カインドにはエンティティのクラス名を指定します。</p>
	 *
	 * @param entityClass エンティティクラス
	 * @param uuid        UUID
	 */
	public DefaultEntityIdentifier(Class<?> entityClass, UUID uuid) {
		this(entityClass.getName(), uuid);
	}
	
	/**
	 * インスタンスを生成する。
	 * <p>{@link UUID}を基にしてエンティティの識別子を生成する</p>
	 *
	 * @param kind カインド
	 * @param uuid {@link UUID}
	 */
	public DefaultEntityIdentifier(String kind, UUID uuid) {
		Validate.notNull(kind);
		Validate.notNull(uuid);
		this.kind = kind;
		this.uuid = uuid;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || !(o instanceof DefaultEntityIdentifier)) {
			return false;
		}
		DefaultEntityIdentifier that = (DefaultEntityIdentifier) o;
		return new EqualsBuilder().append(kind, that.kind).append(uuid, that.kind).isEquals();
	}
	
	@Override
	public String getKind() {
		return kind;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kind).append(uuid).hashCode();
	}
	
	@Override
	public UUID toUUID() {
		return uuid;
	}
}
