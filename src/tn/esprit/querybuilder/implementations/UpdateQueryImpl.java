package tn.esprit.querybuilder.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tn.esprit.querybuilder.interfaces.Field;
import tn.esprit.querybuilder.interfaces.WhereClause;
import tn.esprit.querybuilder.interfaces.UpdateQuery;

/**
 *
 * @author Dhia
 */
class UpdateQueryImpl implements UpdateQuery
{

    /**
     *
     */
    private String m_table;

    /**
     *
     */
    private Map<String, Integer> m_placeholders;

    /**
     *
     */
    private List<Field> m_fields;

    /**
     *
     */
    private boolean m_all;

    /**
     *
     */
    private WhereClause m_where;
	
    /**
     *
     */
    UpdateQueryImpl() { }
	
	@Override
	public String getQueryString()
	{
		if (m_fields == null || m_table == null || m_placeholders == null)
			throw new IllegalStateException("Table name or fields missing");

		if (m_where == null && m_all == false)
			throw new IllegalStateException("Must call all() to update all records");
		
		StringBuilder builder = new StringBuilder("UPDATE ");
		builder.append(Util.protectTableName(m_table));
		builder.append(" SET ");
		
		int fieldCnt = 0;
		for (Field field : m_fields)
		{
			builder.append(field.toString());
			builder.append(" = ?");

			if (fieldCnt != m_fields.size() - 1)
				builder.append(", ");
			fieldCnt++;
		}
		
		if (m_where != null)
			builder.append(m_where.toString());

		return builder.toString();
	}

	@Override
	public int getPlaceholderIndex(String placeholderName)
	{
		if (m_placeholders == null)
			throw new IllegalArgumentException("Placeholder doesn't exist");
		
		Integer idx = m_placeholders.get(placeholderName);
		
		if (idx == null)
		{
			idx = m_where.getPlaceholderIndex(placeholderName);
			if (idx == 0)
				throw new IllegalArgumentException("Placeholder doesn't exist");
		}
		return idx;
	}

	@Override
	public UpdateQuery set(Field field, String placeholder)
	{
		if (m_fields == null)
			m_fields = new ArrayList<Field>();
		if (m_placeholders == null)
			m_placeholders = new HashMap<String, Integer>();
		
		if (m_placeholders.containsKey(placeholder))
			throw new IllegalArgumentException("Duplicate placeholder");
		
		m_fields.add(field);
		m_placeholders.put(placeholder, m_placeholders.size() + 1);
		return this;
	}

	@Override
	public WhereClause where()
	{
		m_where = new WhereClauseImpl(false, m_placeholders == null ? 1 : m_placeholders.size() + 1);
		return m_where;
	}

	@Override
	public UpdateQuery all() {
		m_all = true;
		return this;
	}

	@Override
	public UpdateQuery inTable(String table) {
		m_table = table;
		return this;
	}

}
