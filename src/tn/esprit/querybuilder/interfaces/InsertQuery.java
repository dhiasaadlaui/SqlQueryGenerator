package tn.esprit.querybuilder.interfaces;

/**
 * An interface to generate INSERT queries.
 * @author Dhia
 */
public interface InsertQuery extends Query
{
	/**
	 * Sets a database column placeholder.
	 * @param field
	 * @param placeholder
	 * @return This query builder.
	 */
	public InsertQuery set(Field field, String placeholder);
	
	/**
	 * Sets the table to insert into. Must be called to make a valid insert
	 * statement.
	 * @param table
	 * @return This query builder.
	 */
	public InsertQuery inTable(String table); 
}