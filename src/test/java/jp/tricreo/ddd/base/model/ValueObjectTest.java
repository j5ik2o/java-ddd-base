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

import org.junit.Test;

import sample.PersonName;

/**
 * {@link ValueObject}のためのテスト。
 * 
 * @author j5ik2o
 */
public class ValueObjectTest {
	
	/**
	 * バリューオブジェクトが生成時に指定した属性を保持すること
	 */
	@Test
	public void test01_バリューオブジェクトが生成時に指定した属性を保持すること() {
		String firstName = "Junichi";
		String lastName = "Kato";
		PersonName personName = PersonName.of(firstName, lastName);
		assertThat(personName, is(notNullValue()));
		assertThat(personName.getFirstName(), is(firstName));
		assertThat(personName.getLastName(), is(lastName));
	}
	
	/**
	 * 属性が同じオブジェクトは等価と判定すること
	 */
	@Test
	public void test02_属性が同じオブジェクトは等価と判定すること() {
		String firstName = "Junichi";
		String lastName = "Kato";
		PersonName personName1 = PersonName.of(firstName, lastName);
		PersonName personName2 = PersonName.of(firstName, lastName);
		assertThat(personName1.equals(personName2), is(true));
	}
	
}
