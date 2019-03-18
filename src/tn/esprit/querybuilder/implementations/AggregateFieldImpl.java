package tn.esprit.querybuilder.implementations;

import tn.esprit.querybuilder.interfaces.Field;



/**
 * Immutable class to implement aggregate functions.
 * @author Dhia
 */
class AggregateFieldImpl implements Field
{

    /**
     *
     */
    private final Field m_child;

    /**
     *
     */
    private final String m_func;

    /**
     *
     */
    private final String m_alias;

    /**
     *
     * @param field
     * @param func
     * @param alias
     */
    AggregateFieldImpl(Field field, String func, String alias)
	{
		m_child = field;
		m_func = func;
		m_alias = alias;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(m_func);
		builder.append('(');
		builder.append(m_child.toString());
		builder.append(')');
		
		if (m_alias != null)
		{
			builder.append(" AS ");
			builder.append(m_alias);
		}
		return builder.toString();
	}
}