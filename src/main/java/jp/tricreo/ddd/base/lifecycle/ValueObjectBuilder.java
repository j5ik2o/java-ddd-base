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

import java.util.ArrayList;
import java.util.List;

import jp.tricreo.ddd.base.model.ValueObject;

/**
 * {@link ValueObject}のインスタンスを生成するビルダー。
 *
 * @param <T> ビルド対象のインスタンスの型
 * @param <S> このビルダークラスの型
 */
public abstract class ValueObjectBuilder<T extends ValueObject, S extends ValueObjectBuilder<T, S>> {
	
	/**
	 * {@link ValueObjectBuilder#build()}内で順次実行されるビルダの設定を定義するインタフェース。
	 *
	 * @param <S> 設定対象ビルダーの型
	 */
	public static interface BuilderConfigurator<S> {
		
		/**
		 * {@link ValueObjectBuilder#build()}内で呼ばれる際に実行するビルドアクションを定義する。
		 *
		 * @param builder ビルダーインスタンス
		 */
		void configure(S builder);
		
	}
	

	List<BuilderConfigurator<S>> configurators = new ArrayList<BuilderConfigurator<S>>();
	

	/**
	 * {@link BuilderConfigurator}を追加する。
	 *
	 * @param configurator {@link BuilderConfigurator}
	 */
	protected void addConfigurator(BuilderConfigurator<S> configurator) {
		configurators.add(configurator);
	}
	
	/**
	 * ビルダの設定に基づき、引数の{@link ValueObject}の内容を変更した新しいインスタンスを生成する。
	 *
	 * @param vo 状態を引用する{@link ValueObject}
	 * @return vo の内容に対して、このビルダの設定を上書きした{@link jp.tricreo.ddd.base.model.ValueObject}の新しいインスタンス
	 */
	public T apply(T vo) {
		S builder = newInstance();
		apply(vo, builder);
		
		for (BuilderConfigurator<S> configurator : configurators) {
			builder.addConfigurator(configurator);
		}
		
		return builder.build();
	}
	
	/**
	 * 引数のビルダに対して、引数の{@link ValueObject}の内容を適用する。
	 *
	 * @param vo 状態を引用する{@link ValueObject}
	 * @param builder ビルダ
	 */
	protected abstract void apply(T vo, S builder);
	
	/**
	 * ビルダの設定に基づいて{@link ValueObject}の新しいインスタンスを生成する。
	 *
	 * @return {@link ValueObject}の新しいインスタンス
	 */
	public T build() {
		for (BuilderConfigurator<S> configurator : configurators) {
			configurator.configure(getThis());
		}
		
		return createValueObject();
	}
	
	/**
	 * ビルダの設定に基づいて{@link ValueObject}の新しいインスタンスを生成する。
	 *
	 * <p>
	 * {@link ValueObjectBuilder#build}内でこのビルダに追加された{@link BuilderConfigurator}を全て実行した後に、このメソッドが呼ばれる。<br>
	 * その為、このビルダに対する変更を行うロジックはこのメソッド内に記述せず、目的となる{@link ValueObject}を生成し返すロジックを記述することが望まれる。
	 * </p>
	 *
	 * @return {@link ValueObject}の新しいインスタンス
	 */
	protected abstract T createValueObject();
	
	/**
	 * このビルダークラスのインスタンスを返す。
	 *
	 * @return このビルダークラスのインスタンス。
	 */
	protected abstract S getThis();
	
	/**
	 * このビルダークラスの新しいインスタンスを返す。
	 *
	 * @return このビルダークラスの新しいインスタンス。
	 */
	protected abstract S newInstance();
	
}
