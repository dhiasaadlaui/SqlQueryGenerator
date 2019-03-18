package tn.esprit.querybuilder.implementations;

import tn.esprit.querybuilder.interfaces.Field;


/**
 * Immutable class to implement all fields for a selected table.
 * @author Dhia
 */
class AllTableFieldsImpl implements Field
{

    /**
     *
     */
    private final String m_table;
	
    /**
     *
     * @param table
     */
    AllTableFieldsImpl(String table)
	{
		m_table = table;
	}
	
	@Override
	public String toString() 
	{
		return Util.protectTableName(m_table) + ".*";
	}
}