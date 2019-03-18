package tn.esprit.querybuilder.implementations;

import tn.esprit.querybuilder.interfaces.DeleteQuery;
import tn.esprit.querybuilder.interfaces.WhereClause;

/**
 *
 * @author Dhia
 */
class DeleteQueryImpl implements DeleteQuery
{

    /**
     *
     */
    private String m_table;

    /**
     *
     */
    private boolean m_all;

    /**
     *
     */
    private WhereClause m_where;
	
	@Override
	public String getQueryString() 
	{
		if (m_table == null)
			throw new IllegalStateException("Must specify a table");

		if (m_all == false && m_where == null)
			throw new IllegalStateException("Must call all() to delete all records");
		
		StringBuilder builder = new StringBuilder("DELETE FROM ");
		builder.append(Util.protectTableName(m_table));

		if (m_where != null)
			builder.append(m_where.toString());

		return builder.toString();
	}

	@Override
	public int getPlaceholderIndex(String placeholderName)
	{
		if (m_where != null)
			return m_where.getPlaceholderIndex(placeholderName);
		else
			throw new IllegalArgumentException("Placeholder doesn't exist");
	}

	@Override
	public WhereClause where()
	{
		m_where = new WhereClauseImpl(false, 1);
		return m_where;
	}

	@Override
	public DeleteQuery all()
	{
		m_all = true;
		return this;
	}

	@Override
	public DeleteQuery from(String table) 
	{
		m_table = table;
		return this;
	}
}
