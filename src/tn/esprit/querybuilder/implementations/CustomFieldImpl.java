package tn.esprit.querybuilder.implementations;

import tn.esprit.querybuilder.interfaces.Field;

 

/**
 * Immutable class to implement custom field.
 * @author Dhia
 */
class CustomFieldImpl implements Field
{

    /**
     *
     */
    private final String m_custom;
	
    /**
     *
     * @param custom
     */
    CustomFieldImpl(String custom)
	{
		m_custom = custom;
	}

	@Override
	public String toString() 
	{
		return m_custom;
	}
}