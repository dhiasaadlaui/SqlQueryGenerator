package tn.esprit.querybuilder.interfaces;

/**
 * Interface to generate a SELECT query.
 * @author Dhia
 */
public interface SelectQuery extends Query
{
	/**
	 * Sets the list of fields to select from. Call only once per
 SelectQuery object.
	 * @param fields
	 * @return This query object.
	 */
	public SelectQuery select(Field...fields);

	/**
	 * Which table to get records from? Call only once per
 SelectQuery object. 
	 * @param table
	 * @return This query object.
	 */
	public SelectQuery from(String table);
	
	/**
	 * Creates the where clause builder. This must be called before any
 call to having(). Call only once per SelectQuery object.
	 * @return A mutable WhereClause that is bound to this query.
	 */
	public WhereClause where();
	
	/**
	 * Adds the DISTINCT keyword.
	 * @return This query object.
	 */
	public SelectQuery distinct();
	
	/**
	 * An enumaration of the different join types.
	 */
	public enum JoinType
	{

            /**
             *
             */
            LEFT,

            /**
             *
             */
            RIGHT,

            /**
             *
             */
            OUTER,

            /**
             *
             */
            INNER,

            /**
             *
             */
            LEFT_OUTER,

            /**
             *
             */
            RIGHT_OUTER,

            /**
             *
             */
            DEFAULT
	}

	/**
	 * Join the table to this query. May be called multiple times.
	 * @param table - A table name without backticks.
	 * @param field1 - A field to join on.
	 * @param field2 - The second field to join on.
	 * @param joinType - Join type.
	 * @return This query object.
	 */
	public SelectQuery join(String table, Field field1, Field field2, JoinType joinType);
	
	/**
	 * Similar to {@link #join(String, QbField, QbField, QbJoinType) join}
	 * but uses the db's default join type. May be called multiple times.
	 * @param table - A table name without backticks.
	 * @param field1 - A field to join on.
	 * @param field2 - The second field to join on.
	 * @return This query object.
	 */
	public SelectQuery join(String table, Field field1, Field field2);

	/**
	 * Takes a list of fields to group by. Call only once per
 SelectQuery object.
	 * @param fields - The fields to group by.
	 * @return This query object.
	 */
	public SelectQuery groupBy(Field... fields);
	
	/**
	 * The having clause for use with group_by. This MUST be called after any
 call to where(). Call only once per SelectQuery object.
	 * @return A WhereClause object bound to the having clause of this query.
	 */
	public WhereClause having();
	
	/**
	 * Order ascending or descending. Use this rather than a boolean
	 * to make code more readable.
	 */
	public enum OrderBy
	{

            /**
             *
             */
            ASC,

            /**
             *
             */
            DESC
	}
	
	/**
	 * Used to make the ORDER BY clause. Call only once
 per SelectQuery object.
	 * @param order - ASC or DESC.
	 * @param fields - A list of fields to order by.
	 * @return This query object.
	 */
	public SelectQuery orderBy(OrderBy order, Field... fields);
	
	/**
	 * Allows the provision of an offset and limit.
	 * @param offset - The record offset, starting at zero.
	 * @param count - The number of records to return.
	 * @return This query object.
	 */
	public SelectQuery limit(int offset, int count);
}
