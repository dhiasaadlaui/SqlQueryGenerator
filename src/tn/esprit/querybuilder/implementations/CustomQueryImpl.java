package tn.esprit.querybuilder.implementations;

import tn.esprit.querybuilder.interfaces.Query;

 
/** 
 * Immutable class to implement a custom query.
 * @author Dhia
 */
class CustomQueryImpl implements Query 
{

    /**
     *
     */
    private final String m_sql;
	
    /**
     *
     * @param sql
     */
    CustomQueryImpl(String sql)
	{
		m_sql = sql;
	}
	
	@Override
	public String getQueryString()
	{
		return m_sql;
	}

	@Override
	public int getPlaceholderIndex(String placeholderName)
	{
		throw new IllegalArgumentException("Placeholder doesn't exist");
	}
}