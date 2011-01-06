package sample;


import jp.tricreo.ddd.base.lifecycle.ValueObjectBuilder;

/**
 * {@link PersonName}のためのビルダ実装。
 */
public class PersonNameBuilder extends ValueObjectBuilder<PersonName, PersonNameBuilder> {

    private String firstName;
    private String lastName;

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
    public PersonNameBuilder withLastName(final String lastName){
        addConfigurator(new BuilderConfigurator<PersonNameBuilder>() {
            @Override
            public void configure(PersonNameBuilder builder) {
                builder.lastName = lastName;
            }
        });
        return getThis();
    }


    @Override
    protected void apply(PersonName vo, PersonNameBuilder builder) {
        builder.withFirstName(vo.getFirstName());
        builder.withFirstName(vo.getLastName());
    }

    @Override
    protected PersonName createValueObject() {
        return new PersonName(firstName,lastName);
    }

    @Override
    protected PersonNameBuilder getThis() {
        return this;
    }

    @Override
    protected PersonNameBuilder newInstance() {
        return new PersonNameBuilder();
    }
}
