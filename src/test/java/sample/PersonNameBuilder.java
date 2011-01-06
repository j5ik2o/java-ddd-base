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

import jp.tricreo.ddd.base.lifecycle.ValueObjectBuilder;

/**
 * {@link PersonName}のためのビルダ実装。
 */
public class PersonNameBuilder extends ValueObjectBuilder<PersonName, PersonNameBuilder> {
	
	private String firstName;
	
	private String lastName;
	

	@Override
	protected void apply(PersonName vo, PersonNameBuilder builder) {
		builder.withFirstName(vo.getFirstName());
		builder.withFirstName(vo.getLastName());
	}
	
	@Override
	protected PersonName createValueObject() {
		return new PersonName(firstName, lastName);
	}
	
	@Override
	protected PersonNameBuilder getThis() {
		return this;
	}
	
	@Override
	protected PersonNameBuilder newInstance() {
		return new PersonNameBuilder();
	}
	
	/**
	 * {@link PersonName}に与える名前をこのビルダに設定する。
	 *
	 * @param firstName 名前
	 * @return {@link PersonNameBuilder}
	 */
	public PersonNameBuilder withFirstName(final String firstName) {
		addConfigurator(new BuilderConfigurator<PersonNameBuilder>() {
			
			@Override
			public void configure(PersonNameBuilder builder) {
				builder.firstName = firstName;
			}
		});
		return getThis();
	}
	
	/**
	 * {@link PersonName}に与える苗字をこのビルダに設定する。
	 *
	 * @param lastName 苗字
	 * @return {@link PersonNameBuilder}
	 */
	public PersonNameBuilder withLastName(final String lastName) {
		addConfigurator(new BuilderConfigurator<PersonNameBuilder>() {
			
			@Override
			public void configure(PersonNameBuilder builder) {
				builder.lastName = lastName;
			}
		});
		return getThis();
	}
}
