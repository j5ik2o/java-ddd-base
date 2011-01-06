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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import jp.tricreo.ddd.base.model.impl.DefaultEntityIdentifier;

import org.junit.Test;

import sample.Employee;
import sample.EmployeeFactory;
import sample.PersonName;

/**
 * エンティティのためのテスト。
 * 
 * @author j5ik2o
 */
public class EntityTest {
	
	/**
	 * エンティティが生成時に指定した属性を保持すること
	 */
	@Test
	public void test01_エンティティが生成時に指定した属性を保持すること() {
		UUID uuid = UUID.randomUUID();
		
		EntityIdentifier<Employee> identifier = DefaultEntityIdentifier.of(Employee.class, uuid);
		PersonName name = PersonName.of("Junichi", "Kato");
		Employee employee = new EmployeeFactory(name).create(identifier);
		
		assertThat(employee, is(notNullValue()));
		assertThat(employee.getIdentifier(), is(identifier));
		assertThat(employee.getName(), is(name));
	}
	
	/**
	 * 属性が異なっても識別子が同じなら等価と判定すること
	 */
	@Test
	public void test02_属性が異なっても識別子が同じなら等価と判定すること() {
		UUID uuid = UUID.randomUUID();
		EntityIdentifier<Employee> identifier = DefaultEntityIdentifier.of(Employee.class, uuid);
		PersonName name1 = PersonName.of("Junichi", "Kato");
		Employee employee1 = new EmployeeFactory(name1).create(identifier);
		
		PersonName name2 = PersonName.of("JUNICHI", "KATO");
		Employee employee2 = new EmployeeFactory(name2).create(identifier);
		
		assertThat(employee1.getName().equals(employee2.getName()), is(false));
		assertThat(employee1.equals(employee2), is(true));
	}
	
}
