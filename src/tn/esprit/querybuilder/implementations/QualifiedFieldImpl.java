package tn.esprit.querybuilder.implementations;

import tn.esprit.querybuilder.interfaces.Field;

 

/**
 * Immutable class to implement qualified fields.
 * @author Dhia
 */
class QualifiedFieldImpl implements Field
{

    /**
     *
     */
    private final String m_table;

    /**
     *
     */
    private final String m_field;
	
    /**
     *
     * @param table
     * @param field
     */
    QualifiedFieldImpl(String table, String field) 
	{
		m_table = table;
		m_field = field;
	}
	
	@Override
	public String toString()
	{
		return Util.protectTableName(m_table) + ".`" + m_field + '`';
	}
}