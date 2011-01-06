package sample;

import jp.tricreo.ddd.base.model.ValueObject;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 個人名を表すバリューオブジェクト。
 * 
 * @author j5ik2o
 */
public final class PersonName implements ValueObject {
	
	/**
	 * ファクトリメソッド。
	 * 
	 * @param firstName 名
	 * @param lastName  姓
	 * @return {@link PersonName}
	 */
	public static PersonName of(String firstName, String lastName) {
		return new PersonName(firstName, lastName);
	}
	

	private final String firstName;
	
	private final String lastName;
	

	/**
	 * インスタンスを生成する。
	 *
	 * @param firstName 名
	 * @param lastName  姓
	 */
	public PersonName(String firstName, String lastName) {
		Validate.notNull(firstName);
		Validate.notNull(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PersonName that = (PersonName) o;
		return new EqualsBuilder().append(firstName, that.firstName).append(lastName, that.lastName).isEquals();
	}
	
	/**
	 * 名を取得する。
	 * 
	 * @return 名
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 姓を取得する。
	 * 
	 * @return 姓
	 */
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(firstName).append(lastName).toHashCode();
	}
}
