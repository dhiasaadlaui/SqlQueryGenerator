package tn.esprit.querybuilder.interfaces;

/**
 * Interface to generate an update query.
 * @author Dhia
 */
public interface UpdateQuery extends Query
{
	/**
	 * Sets a field to the specified placeholder. May be called 
	 * multiple times.
	 * @param field
	 * @param placeholder
	 * @return This query builder.
	 */
	public UpdateQuery set(Field field, String placeholder);
	
	/**
	 * Adds a where clause. Very important. Call only once
 per UpdateQuery object.
	 * @return A WhereClause object that is bound to this query.
	 */
	public WhereClause where();
	
	/**
	 * Signals that you want to update all records. If neither
	 * where or all is called the query builder will throw an exception.
	 * @return This query builder.
	 */
	public UpdateQuery all();
	
	/**
	 * Which table to update. Table name should not contain
	 * backticks. Must be called to make a valid update query.
	 * @param table
	 * @return This query builder.
	 */
	public UpdateQuery inTable(String table);
}