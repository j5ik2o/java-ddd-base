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

import java.util.List;
import java.util.Set;

import jp.tricreo.ddd.base.lifecycle.exception.EntityMultipleFoundRuntimeException;
import jp.tricreo.ddd.base.lifecycle.exception.EntityNotFoundRuntimeException;
import jp.tricreo.ddd.base.lifecycle.exception.RepositoryRuntimeException;
import jp.tricreo.ddd.base.model.Entity;
import jp.tricreo.ddd.base.model.EntityIdentifier;

import com.google.common.base.Predicate;

/**
 * リポジトリを表すインターフェイス。
 *
 * @param <T> エンティティの型
 */
public interface Repository<T extends Entity<T>> {
	
	/**
	 * このリポジトリに格納されているすべてのエンティティをListで取得する。
	 *
	 * @return すべてのエンティティのList
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	List<T> asEntitiesList();
	
	/**
	 * このリポジトリに格納されているすべてのエンティティをSetで取得する。
	 *
	 * @return すべてのエンティティのSet
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Set<T> asEntitiesSet();
	
	/**
	 * 指定した識別子のエンティティが存在するかを返す。
	 *
	 * @param identifier 識別子
	 * @return 存在する場合はtrue
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	boolean contains(EntityIdentifier<T> identifier);
	
	/**
	 * 指定した述語が該当するかを返す。
	 * <p>識別子以外を用いる複雑な条件の場合に利用する。</p>
	 *
	 * @param predicate エンティティ
	 * @return 存在する場合はtrue
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	boolean contains(Predicate<T> predicate);
	
	/**
	 * 指定したエンティティが存在するかを返す。
	 *
	 * @param entity エンティティ
	 * @return 存在する場合はtrue
	 * @throws jp.tricreo.ddd.base.lifecycle.exception.RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	boolean contains(T entity);
	
	/**
	 * 指定した識別子のエンティティを削除する。
	 *
	 * @param identifier 識別子
	 * @throws EntityNotFoundRuntimeException 指定された識別子を持つエンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	void delete(EntityIdentifier<T> identifier);
	
	/**
	 * 指定したエンティティを削除する。
	 *
	 * @param entity エンティティ
	 * @throws EntityNotFoundRuntimeException 指定された識別子を持つエンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	void delete(T entity);
	
	/**
	 * 識別子に該当するエンティティを取得する。
	 *
	 * @param identifier 識別子
	 * @return エンティティ
	 * @throws IllegalArgumentException       引数が不正な場合
	 * @throws EntityNotFoundRuntimeException エンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	T resolve(EntityIdentifier<T> identifier);
	
	/**
	 * 述語に該当するエンティティを取得する。
	 * <p>識別子以外を用いる複雑な条件の場合に利用する。
	 * 述語に複数のエンティティが該当する場合は最初に発見したエンティティを返す。</p>
	 *
	 * @param predicate 述語
	 * @return エンティティ
	 * @throws IllegalArgumentException       引数が不正な場合
	 * @throws EntityNotFoundRuntimeException エンティティが見つからなかった場合
	 * @throws EntityMultipleFoundRuntimeException 熟語に対して複数のエンティティが見つかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	T resolve(Predicate<T> predicate);
	
	/**
	 * エンティティを保存する。
	 *
	 * @param entity 保存する対象のエンティティ
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	void store(T entity);
}
