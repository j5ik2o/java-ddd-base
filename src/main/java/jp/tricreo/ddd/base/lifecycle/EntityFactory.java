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
package jp.tricreo.ddd.base.lifecycle;

import jp.tricreo.ddd.base.model.Entity;
import jp.tricreo.ddd.base.model.EntityIdentifier;

/**
 * {@link Entity}のためのファクトリ。
 *
 * @param <T> エンティティの型 
 * @author j5ik2o
 */
public interface EntityFactory<T extends Entity<T>> {
	
	/**
	 * ファクトリの状態に基づいて {@link Entity}のインスタンスを生成する。
	 * <p>エンティティの識別子は自動生成される。</p>
	 *
	 * @return 新しい {@link Entity}のインスタンス
	 */
	T create();
	
	/**
	 * ファクトリの状態に基づいて {@link Entity}のインスタンスを生成する。
	 *
	 * @param identifier エンティティの識別子
	 * @return 新しい {@link Entity}のインスタンス
	 * @throws IllegalArgumentException 引数{@code identifier}に{@code null}を与えた場合
	 */
	T create(EntityIdentifier<T> identifier);
	
}
