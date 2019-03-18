package tn.esprit.querybuilder.interfaces;

/**
 * Factory interface to create query-builder objects such as
 * fields and queries.
 * Concretely implemented by a class for each database. 
 * @author Dhia
 */
public interface QueriesFactory
{
	/**
	 * Creates an all fields selector.
	 * @return Field
	 */
	public Field newAllField();
	
	/**
	 * Creates an all fields selector for the specified table.
	 * @param table - A table name without backticks.
	 * @return Field
	 */
	public Field newAllField(String table);
	
	/**
	 * Creates a standard database field. Will enclose the field
	 * in backticks in the final query.
	 * @param name - A table column without backticks.
	 * @return Field
	 */
	public Field newStdField(String name);

	/**
	 * Creates a qualified database field. Will enclose the field
	 * and table name in backticks in the final query.
	 * @param table - A table name without backticks.
	 * @param name - A table column without backticks.
	 * @return Field
	 */
	public Field newQualifiedField(String table, String name);
	
	/**
	 * Creates a SUM aggregate function on the specified field.
	 * @param field - Returned by newStdField.
	 * @param alias - A string to use with AS. May be null
	 * in which case no AS is used.
	 * @return Field
	 */
	public Field newSum(Field field, String alias);

	/**
	 * Creates a count aggregate function on the specified field.
	 * @param field - Returned by newStdField.
	 * @param alias - A string to use with AS. May be null
	 * in which case no AS is used.
	 * @return Field
	 */
	public Field newCount(Field field, String alias);
	
	/**
	 * Creates an average aggregate function on the specified field.
	 * @param field - Returned by newStdField.
	 * @param alias - A string to use with AS. May be null
	 * in which case no AS is used.
	 * @return Field
	 */
	public Field newAvg(Field field, String alias);
	
	/**
	 * Creates a minimum function on the specified field.
	 * @param field - Returned by newStdField.
	 * @param alias - A string to use with AS. May be null
	 * in which case no AS is used.
	 * @return Field
	 */
	public Field newMin(Field field, String alias);
	
	/**
	 * Creates a count aggregate function on the specified field.
	 * @param field - Returned by newStdField.
	 * @param alias - A string to use with AS. May be null
	 * in which case no AS is used.
	 * @return Field
	 */
	public Field newMax(Field field, String alias);
	
	/**
	 * Creates a custom field.
	 * @param custom - A custom string that will be used as is.
	 * @return Field
	 */
	public Field newCustomField(String custom);
	
	/**
	 * Creates a SELECT query. 
     * @return 
	 */
	public SelectQuery newSelectQuery();

	/**
	 * Creates an UPDATE query.
     * @return 
	 */
	public UpdateQuery newUpdateQuery();

	/**
	 * Creates a DELETE query.
     * @return 
	 */
	public DeleteQuery newDeleteQuery();

	/**
	 * Creates an INSERT query.
     * @return 
	 */
	public InsertQuery newInsertQuery();
	
	/**
	 * Creates a query object with the given sql.
	 * Only use this if the query builder can not build an appropriate
	 * query.
	 * @param sql - A custom sql string.
	 * @return Query
	 */
	public Query newQuery(String sql);
}