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

/**
 * エンティティを表すインターフェイス。
 *
 * @param <T> エンティティの型 
 * @author j5ik2o
 */
public interface Entity<T extends Entity<T>> extends Cloneable {
	
	/**
	 * このエンティティの複製を生成する。
	 * 
	 * @return このエンティティの複製。
	 */
	T clone();
	
	/**
	 * エンティティの{@link #getIdentifier() 識別子}を用いて、このエンティティの同一性を比較する。
	 *
	 * @param that 比較対象オブジェクト
	 * @return 同じ識別子を持つ場合は{@code true}
	 */
	@Override
	boolean equals(Object that);
	
	/**
	 * エンティティの識別子を取得する。
	 * 
	 * @return {@link EntityIdentifier}
	 */
	EntityIdentifier<T> getIdentifier();
	
	/**
	 * このエンティティのハッシュコードを返す。
	 * <p>Effective Java 第二版 項目9に従い、equalsメソッドを
	 * オーバーライドするときは必ずhashCodeメソッドもオーバーライドする。</p>
	 *
	 * @return ハッシュコード
	 */
	@Override
	int hashCode();
}
