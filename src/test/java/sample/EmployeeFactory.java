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

import java.util.UUID;

import jp.tricreo.ddd.base.lifecycle.EntityFactory;
import jp.tricreo.ddd.base.model.Identifier;
import jp.tricreo.ddd.base.model.impl.DefaultEntityIdentifier;

/**
 * {@link Employee}のためのファクトリ実装。
 *
 * @author j5ik2o
 */
public class EmployeeFactory implements EntityFactory<Employee> {
	
	private PersonName name;
	

	/**
	 * インスタンスを生成する。
	 *
	 * @param name {@link PersonName}
	 */
	public EmployeeFactory(PersonName name) {
		this.name = name;
	}
	
	@Override
	public Employee create() {
		Identifier identifier = DefaultEntityIdentifier.of(Employee.class, UUID.randomUUID());
		return create(identifier);
	}
	
	@Override
	public Employee create(Identifier identifier) {
		return new Employee(identifier, name);
	}
}
