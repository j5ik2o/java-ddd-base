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
package sample;

import jp.tricreo.ddd.base.model.Identifier;
import jp.tricreo.ddd.base.model.impl.AbstractEntity;

/**
 * 従業員を表すエンティティ。
 * 
 * @author j5ik2o
 */
public final class Employee extends AbstractEntity {
	
	private PersonName name;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param identifier {@link Identifier}
	 * @param name {@link PersonName}
	 */
	public Employee(Identifier identifier, PersonName name) {
		super(identifier);
		this.name = name;
	}
	
	/**
	 * {@link PersonName}を取得する。
	 * 
	 * @return {@link PersonName}
	 */
	public PersonName getName() {
		return name;
	}
	
	/**
	 * {@link PersonName}を設定する。
	 *  
	 * @param name {@link PersonName}
	 */
	public void setName(PersonName name) {
		this.name = name;
	}
	
}
