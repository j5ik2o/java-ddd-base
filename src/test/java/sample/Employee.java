package sample;

import jp.tricreo.ddd.base.model.EntityIdentifier;
import jp.tricreo.ddd.base.model.impl.AbstractEntity;

/**
 * 従業員を表すエンティティ。
 * 
 * @author j5ik2o
 */
public final class Employee extends AbstractEntity<Employee> {
	
	private PersonName name;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param identifier {@link EntityIdentifier}
	 * @param name {@link PersonName}
	 */
	public Employee(EntityIdentifier<Employee> identifier, PersonName name) {
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
