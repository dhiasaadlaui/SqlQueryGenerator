package tn.esprit.querybuilder.implementations;

import java.util.ArrayList;
import java.util.List;
import tn.esprit.querybuilder.interfaces.Field;
import tn.esprit.querybuilder.interfaces.SelectQuery;
import tn.esprit.querybuilder.interfaces.WhereClause;

/**
 *
 * @author Dhia
 */
class SelectQueryImpl implements SelectQuery
{

    /**
     *
     */
    private int m_offset;

    /**
     *
     */
    private int m_limit;

    /**
     *
     */
    private boolean m_haveLimit;

    /**
     *
     */
    private Field[] m_orderBy;

    /**
     *
     */
    private OrderBy m_orderByOrder;

    /**
     *
     */
    private WhereClause m_havingClause;

    /**
     *
     */
    private WhereClause m_where;

    /**
     *
     */
    private Field[] m_groupBy;

    /**
     *
     */
    SelectQueryImpl() { }
	
    /**
     *
     */
    private class JoinInfo
	{

        /**
         *
         */
        final Field leftSide;

            /**
             *
             */
            final Field rightSide;

            /**
             *
             */
            final JoinType joinType;

            /**
             *
             */
            final String table;

        /**
         *
         * @param left
         * @param right
         * @param tabl
         * @param type
         */
        JoinInfo(Field left, Field right, String tabl, JoinType type)
		{
			leftSide = left;
			rightSide = right;
			joinType = type;
			table = tabl;
		}
	}
	
    /**
     *
     */
    private List<JoinInfo> m_joinList;

    /**
     *
     */
    private Field[] m_selectFields;

    /**
     *
     */
    private boolean m_distinct;

    /**
     *
     */
    private String m_table;

    /**
     *
     * @param joinType
     * @return
     */
    private String joinTypeToString(JoinType joinType)
	{
		switch (joinType)
		{
		case DEFAULT:
			return "";
		case LEFT_OUTER:
			return "LEFT OUTER";
		case RIGHT_OUTER:
			return "RIGHT OUTER";
		case INNER:
		case OUTER:
		case LEFT:
		case RIGHT:
		default:
			return joinType.toString();
		}
	}
	
	
	@Override
	public String getQueryString()
	{
		StringBuilder builder = new StringBuilder("SELECT ");
		
		if (m_table == null)
			throw new IllegalStateException("Must specify table");
		
		if (m_selectFields == null)
			throw new IllegalStateException("Must specify some fields");
		
		if (m_distinct)
			builder.append("DISTINCT ");
		
		Util.joinFieldNames(builder, m_selectFields);
		builder.append(" FROM ");
		builder.append(Util.protectTableName(m_table));
		builder.append(' ');
		
	
		if (m_joinList != null)
		{
			for (JoinInfo join : m_joinList)
			{
				builder.append(joinTypeToString(join.joinType));
				builder.append(" JOIN ");
				builder.append(Util.protectTableName(join.table));
				builder.append(" ON ");
				builder.append(join.leftSide.toString());
				builder.append(" = ");
				builder.append(join.rightSide.toString());
			}
		}

		if (m_where != null)
			builder.append(m_where.toString());
		
		if (m_groupBy != null)
		{
			builder.append(" GROUP BY ");
			Util.joinFieldNames(builder, m_groupBy);
		}

		if (m_havingClause != null)
			builder.append(m_havingClause.toString());
		
		if (m_orderBy != null)
		{
			builder.append(" ORDER BY ");
			Util.joinFieldNames(builder, m_orderBy);
			builder.append(' ');
			builder.append(m_orderByOrder.toString());
		}
		
		if (m_haveLimit)
		{
			builder.append(" LIMIT ");
			builder.append(m_offset);
			builder.append(", ");
			builder.append(m_limit);
		}
			
		return builder.toString();
	}

	@Override
	public int getPlaceholderIndex(String placeholderName)
	{
		int idx = 0;
		if (m_havingClause != null)
			idx = m_havingClause.getPlaceholderIndex(placeholderName);
		
		if (idx == 0 && m_where != null)
			idx = m_where.getPlaceholderIndex(placeholderName);
		
		if (idx == 0)
			throw new IllegalArgumentException("Placeholder doesn't exist");
		
		return idx;
	}

	@Override
	public SelectQuery select(Field... fields)
	{
		m_selectFields = fields;
		return this;
	}

	@Override
	public SelectQuery from(String table)
	{
		m_table = table;
		return this;
	}

	@Override
	public WhereClause where()
	{
		m_where = new WhereClauseImpl(false, 1);
		return m_where;
	}

	@Override
	public SelectQuery distinct()
	{
		m_distinct = true;
		return this;
	}

	@Override
	public SelectQuery join(String table, Field field1, Field field2,
			JoinType joinType) 
	{
		if (m_joinList == null)
			m_joinList = new ArrayList<JoinInfo>(2);
		
		m_joinList.add(new JoinInfo(field1, field2, table, joinType));
		return this;
	}

	@Override
	public SelectQuery join(String table, Field field1, Field field2)
	{
		if (m_joinList == null)
			m_joinList = new ArrayList<JoinInfo>(2);
		
		m_joinList.add(new JoinInfo(field1, field2, table, JoinType.DEFAULT));
		return this;
	}

	@Override
	public SelectQuery groupBy(Field... fields)
	{
		m_groupBy = fields;
		return this;
	}

	@Override
	public WhereClause having()
	{
		m_havingClause = new WhereClauseImpl(true, m_where == null ? 1 : m_where.getPlaceholderCount() + 1);
		return m_havingClause;
	}

	@Override
	public SelectQuery orderBy(OrderBy order, Field... fields)
	{
		m_orderBy = fields;
		m_orderByOrder = order;
		return this;
	}

	@Override
	public SelectQuery limit(int offset, int count)
	{
		m_offset = offset;
		m_limit = count;
		m_haveLimit = true;
		return this;
	}
}