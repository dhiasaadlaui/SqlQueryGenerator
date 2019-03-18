package tn.esprit.querybuilder.implementations;

import tn.esprit.querybuilder.interfaces.QueriesFactory;
import tn.esprit.querybuilder.interfaces.DeleteQuery;
import tn.esprit.querybuilder.interfaces.Field;
import tn.esprit.querybuilder.interfaces.InsertQuery;
import tn.esprit.querybuilder.interfaces.Query;
import tn.esprit.querybuilder.interfaces.SelectQuery;
import tn.esprit.querybuilder.interfaces.UpdateQuery;

/**
 *
 * @author Dhia
 */
public class QueriesFactoryImpl implements QueriesFactory
{

    /**
     *
     */
    private static Field m_allField = new AllFields();
	
	@Override
	public Field newAllField()
	{
		return m_allField;
	}

	@Override
	public Field newAllField(String table)
	{
		return new AllTableFieldsImpl(table);
	}

	@Override
	public Field newStdField(String name)
	{
		return new StandardFieldImpl(name);
	}

	@Override
	public Field newQualifiedField(String table, String name)
	{
		return new QualifiedFieldImpl(table, name);
	}

	@Override
	public Field newSum(Field field, String alias)
	{
		return new AggregateFieldImpl(field, "SUM", alias);
	}

	@Override
	public Field newCount(Field field, String alias) 
	{
		return new AggregateFieldImpl(field, "COUNT", alias);
	}

	@Override
	public Field newAvg(Field field, String alias)
	{
		return new AggregateFieldImpl(field, "AVG", alias);
	}

	@Override
	public Field newMin(Field field, String alias)
	{
		return new AggregateFieldImpl(field, "MIN", alias);
	}

	@Override
	public Field newMax(Field field, String alias)
	{
		return new AggregateFieldImpl(field, "MAX", alias);
	}

	@Override
	public Field newCustomField(String custom)
	{
		return new CustomFieldImpl(custom);
	}

	@Override
	public SelectQuery newSelectQuery()
	{
		return new SelectQueryImpl();
	}

	@Override
	public UpdateQuery newUpdateQuery()
	{
		return new UpdateQueryImpl();
	}

	@Override
	public DeleteQuery newDeleteQuery()
	{
		return new DeleteQueryImpl();
	}

	@Override
	public InsertQuery newInsertQuery()
	{
		return new InsertQueryImpl();
	}

	@Override
	public Query newQuery(String sql)
	{
		return new CustomQueryImpl(sql);
	}
}