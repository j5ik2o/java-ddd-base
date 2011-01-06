package sample;

import java.util.UUID;

import jp.tricreo.ddd.base.lifecycle.EntityFactory;
import jp.tricreo.ddd.base.model.EntityIdentifier;
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
		EntityIdentifier<Employee> identifier = DefaultEntityIdentifier.of(Employee.class, UUID.randomUUID());
		return create(identifier);
	}
	
	@Override
	public Employee create(EntityIdentifier<Employee> identifier) {
		return new Employee(identifier, name);
	}
}
