package tn.esprit.querybuilder.implementations;

import tn.esprit.querybuilder.interfaces.Field;

 

/**
 * Immutable class to implement a standard un-qualified field.
 * @author Dhia
 */
class StandardFieldImpl implements Field
{

    /**
     *
     */
    private final String m_fieldName;

    /**
     *
     * @param field
     */
    StandardFieldImpl(String field)
	{
		m_fieldName = field;
	}
	
	@Override
	public String toString() 
	{
		return '`' + m_fieldName + '`';
	}
}