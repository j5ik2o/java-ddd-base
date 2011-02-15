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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

import org.junit.Test;

/**
 * TODO for junichi
 * 
 * @since TODO for junichi
 * @version $Id$
 * @author junichi
 */
public class GCTest {
	
	@Test
	public void test() {
		
		Set<Employee> employees = new HashSet<Employee>();
		
		employees.add(new EmployeeFactory(PersonName.of("Junichi", "Kato")).create());
		employees.add(new EmployeeFactory(PersonName.of("JUNICHI", "Kato")).create());
		employees.add(new EmployeeFactory(PersonName.of("Junichi", "KATO")).create());
		employees.add(new EmployeeFactory(PersonName.of("JUNICHI", "KATO")).create());
		
		Predicate<Employee> predicate1 = new Predicate<Employee>() {
			
			@Override
			public boolean apply(Employee input) {
				return input.getName().getFirstName().equals("Junichi");
			}
			
		};
		Set<Employee> filter = Sets.filter(employees, predicate1);
		
		assertThat(filter, is(notNullValue()));
		assertThat(filter.size(), is(2));
		
		Predicate<Employee> predicate2 = new Predicate<Employee>() {
			
			@Override
			public boolean apply(Employee input) {
				return input.getName().getLastName().equals("KATO");
			}
			
		};
		
		Set<Employee> filter2 = Sets.filter(employees, Predicates.and(predicate1, predicate2));
		
		assertThat(filter2, is(notNullValue()));
		assertThat(filter2.size(), is(1));
		System.out.println(filter2.iterator().next());
		
	}
	
	@Test
	public void test2() {
		Collection<PersonName> personNames = new HashSet<PersonName>();
		
		personNames.add(PersonName.of("Junichi", "Kato"));
		personNames.add(PersonName.of("JUNICHI", "Kato"));
		personNames.add(PersonName.of("Junichi", "KATO"));
		personNames.add(PersonName.of("JUNICHI", "KATO"));
		
		Function<PersonName, String> fullNameFunction = new Function<PersonName, String>() {
			
			@Override
			public String apply(PersonName from) {
				return from.getFirstName() + "." + from.getLastName();
			}
		};
		
		Collection<String> transform = Collections2.transform(personNames, fullNameFunction);
		for (String fullName : transform) {
			System.out.println(fullName);
		}
		
		Function<PersonName, PersonName> personNameFunction = new Function<PersonName, PersonName>() {
			
			@Override
			public PersonName apply(PersonName from) {
				return new PersonName(from.getLastName(), from.getFirstName());
			}
		};
		
		Function<PersonName, String> compose = Functions.compose(fullNameFunction, personNameFunction);
		Collection<String> transform2 = Collections2.transform(personNames, compose);
		for (String fullName : transform2) {
			System.out.println(fullName);
		}
	}
	
	public void test3() {
		
	}
}
