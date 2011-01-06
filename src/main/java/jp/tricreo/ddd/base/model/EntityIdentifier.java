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
package jp.tricreo.ddd.base.model;

import java.util.UUID;

/**
 * エンティティの識別子を表すバリューオブジェクト。
 *
 * @param <T> エンティティの型
 * @author j5ik2o
 */
public interface EntityIdentifier<T extends Entity<T>> extends ValueObject {
	
	/**
	 * この識別子の種類(カインド)を取得する。
	 * <p>通常はエンティティのFQCNが格納されている</p>
	 *
	 * @return カインド
	 */
	String getKind();
	
	/**
	 * この識別子を{@link UUID}に変換する。
	 *
	 * @return {@link UUID}
	 */
	UUID toUUID();
	
}
