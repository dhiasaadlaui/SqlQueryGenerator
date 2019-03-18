package tn.esprit.querybuilder.interfaces;

/**
 * An interface to build DELETE queries. 
 * @author Dhia
 */
public interface DeleteQuery extends Query
{
	/**
	 * Sets the WHERE clause.
	 * @return A WhereClause object which is bound to this query.
	 */
	public WhereClause where();
	
	/**
	 * Marks all records for deletion. Either this method
	 * or where must be called. 
	 * @return This query builder.
	 */
	public DeleteQuery all();
	
	/**
	 * The table you want to delete from. Must be called to make
	 * a valid delete statement.
	 * @param table - A table name without backticks.
	 * @return This query builder.
	 */
	public DeleteQuery from(String table);
}
