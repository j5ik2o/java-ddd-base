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
 * ドメインイベントを表すインターフェイス。
 * 
 * @version $Id$
 * @author j5ik2o
 */
public interface DomainEvent extends DomainModel {
	
	/**
	 * 集約ルートの識別子を取得する。
	 * 
	 * @return 集約ルートの識別子
	 */
	Identifier getAggregateRootId();
	
	/**
	 * 識別子を取得する。
	 * 
	 * @return {@link Identifier}
	 */
	Identifier getIdentifier();
	
	/**
	 * バージョンを取得する。
	 * 
	 * @return バージョン
	 */
	Long getVersion();
	
}
