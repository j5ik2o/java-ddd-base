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
package jp.tricreo.ddd.base.lifecycle.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import jp.tricreo.ddd.base.model.Identifier;
import jp.tricreo.ddd.base.model.impl.DefaultEntityIdentifier;

import com.google.common.base.Predicate;

import org.junit.Before;
import org.junit.Test;

import sample.Employee;
import sample.EmployeeFactory;
import sample.PersonName;

/**
 * {@link OnMemoryRepository}のためのテスト。
 * 
 * @author j5ik2o
 */
public class OnMemoryRepositoryTest {
	
	private OnMemoryRepository<Employee> repository = new OnMemoryRepository<Employee>();
	
	private Identifier identifier;
	
	private Employee employee;
	

	/**
	 * TODO for junichi
	 * 
	 * @since TODO
	 */
	@Before
	public void setUp() {
		UUID uuid = UUID.randomUUID();
		
		identifier = DefaultEntityIdentifier.of(Employee.class, uuid);
		PersonName name = PersonName.of("Junichi", "Kato");
		employee = new EmployeeFactory(name).create(identifier);
		
		repository.store(employee);
		
	}
	
	/**
	 * 識別子でエンティティを取得できること
	 */
	@Test
	public void test01_識別子でエンティティを取得できること() {
		Employee resolve = repository.resolve(identifier);
		assertThat(resolve, is(notNullValue()));
		assertThat(resolve, is(employee));
		assertThat(resolve.getIdentifier(), is(employee.getIdentifier()));
	}
	
	/**
	 * 熟語でエンティティを取得できること
	 */
	@Test
	public void test02_熟語でエンティティを取得できること() {
		Employee resolve = repository.resolve(new Predicate<Employee>() {
			
			@Override
			public boolean apply(Employee input) {
				return input.getIdentifier().equals(identifier);
			}
		});
		assertThat(resolve, is(notNullValue()));
		assertThat(resolve, is(employee));
		assertThat(resolve.getIdentifier(), is(employee.getIdentifier()));
		
		Employee predicateEmployee = new EmployeeFactory(PersonName.of("Junichi", "KATO")).create();
		repository.store(predicateEmployee);
		
		Employee resolve2 = repository.resolve(new Predicate<Employee>() {
			
			@Override
			public boolean apply(Employee input) {
				return input.getName().getLastName().equals("KATO");
			}
		});
		assertThat(resolve2, is(notNullValue()));
		assertThat(resolve2, is(predicateEmployee));
		assertThat(resolve2.getIdentifier(), is(predicateEmployee.getIdentifier()));
	}
	
}
