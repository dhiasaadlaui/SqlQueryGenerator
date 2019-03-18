
# SqlQueryGenerator
## Inspired from https://www.jooq.org/
## JavaDoc available in the link below
# https://dhiasaadlaui.github.io/QueryGeneratorJavaDoc/
This project is an sql queries generator using builder and factory patterns, 
'IMPORTANT': this project build sql queries as string format,it can be used to instantiate PreparedStatements & inject fields
in a given Placeholders, IT DOESN'T WORK LIKE ORMs.
As a QueryGenerator user you can dynamically generate SQL queries as a string.

## Output Examples with PreparedStatement execution

 QueriesFactory must be used for creating instances of Queries and Fields below.
 * SelectQuery
 * UpdateQuery
 * DeleteQuery
 * InsertQuery
 * Field
 
 	
	`QueriesFactory queryFactory = new QueriesFactoryImpl();`
 
## Update username, adress and phone for a given id...
	UpdateQuery updateQuery = queryFactory.newUpdateQuery();
	updateQuery.set(queryFactory.newStdField("username"), ":username")
		.set(queryFactory.newStdField("adress"), ":adress")
		.set(queryFactory.newStdField("phone"), ":phone")
		.inTable("person")
		.where()
		.where(queryFactory.newStdField("id"), ":id");
		  
	System.out.println(updateQuery.getQueryString());
* *Output:*  

UPDATE `person` 

SET \`username\` = ?, \`adress\` = ?, \`phone\` = ? 

WHERE \`id\` = ?  
  
    stmt = conn.prepareStatement(updateQuery.getQueryString());
		stmt.setString(updateQuery.getPlaceholderIndex(":username"), "John");
		stmt.setString(updateQuery.getPlaceholderIndex(":adress"), "Citizen");
		stmt.setString(updateQuery.getPlaceholderIndex(":phone"), "+ 216 71 555 666");
		stmt.setInt(updateQuery.getPlaceholderIndex(":id"), 300);
		stmt.executeUpdate();
		
## Delete all persons with a given id ...
	DeleteQuery deleteQuery = queryFactory.newDeleteQuery();
	deleteQuery.from("Person")
	   .where()
	   .where(queryFactory.newStdField("id"), ":id");
		   
	System.out.println(deleteQuery.getQueryString());
* *Output:*  		

DELETE 

FROM `Person` 

WHERE `id` = ?

	stmt = conn.prepareStatement(deleteQuery.getQueryString());
		stmt.setInt(deleteQuery.getPlaceholderIndex(":id"), 1300);
		stmt.executeUpdate();	
	
## Insert a person with the confirmed field set to true...
	InsertQuery insertQuery = queryFactory.newInsertQuery();
	insertQuery.set(queryFactory.newStdField("confirmed"), ":confirmed")
	 .inTable("Person");
* *Output:*  		

INSERT INTO `Person` (\`confirmed\`) VALUES (?)
	
	stmt = conn.prepareStatement(insertQuery.getQueryString());
		stmt.setBoolean(insertQuery.getPlaceholderIndex(":confirmed"), true);
		stmt.executeUpdate();
	
## Simple select query, retrieves id, age and last_name for records which don't have the given id...
	SelectQuery selectQuery = queryFactory.newSelectQuery();
	selectQuery.select(queryFactory.newStdField("id"), queryFactory.newStdField("age"), queryFactory.newStdField("last_name"))
		.from("Person")
		.where()
		.where(queryFactory.newStdField("id"), WhereOperator.NOT_EQUALS, ":id");
* *Output:*  		

SELECT \`id\`, \`age\`, \`last_name\`

FROM  `Person` 

WHERE \`id\` <> ?

		stmt = conn.prepareStatement(selectQuery.getQueryString());
		stmt.setInt(selectQuery.getPlaceholderIndex(":id"), 5543);
		ResultSet rs = stmt.executeQuery();		

## Select query with left join...
		SelectQuery selectQuery = queryFactory.newSelectQuery();
		selectQuery.select(queryFactory.newQualifiedField("TABLE1", "id"), queryFactory.newQualifiedField("TABLE2", "id"))
		.from("TABLE1")
		.join("TABLE2", queryFactory.newQualifiedField("TABLE2", "id"), queryFactory.newQualifiedField("TABLE1", "t1_id"), 				JoinType.LEFT)
		.where()
		.where(queryFactory.newStdField("age"), ":age");
* *Output:*  

SELECT `TABLE1`.\`id\`, `TABLE2`.\`id\` 

FROM `TABLE1` LEFT JOIN `TABLE2` 

ON `TABLE2`.\`id\` = `TABLE1`.\`t1_id\` 

WHERE \`age\` = ?
		
		stmt = conn.prepareStatement(selectQuery.getQueryString());
		stmt.setInt(selectQuery.getPlaceholderIndex(":age"), 45);
		rs = stmt.executeQuery();
	
## Select query with group by...
		SelectQuery selectQuery = queryFactory.newSelectQuery();
		selectQuery.select(queryFactory.newAvg(queryFactory.newStdField("age"), "avg_age"))
		.from("Person")
		.groupBy(queryFactory.newStdField("nationality"))
		.having()
		.where(queryFactory.newStdField("avg_age"), WhereOperator.GREATER_THAN, ":avg_age");
		
* *Output:*  

SELECT AVG(\`age\`) AS avg_age 

FROM `Person`  

GROUP BY \`nationality\` 

HAVING \`avg_age\` > ?
		
		stmt = conn.prepareStatement(selectQuery.getQueryString());
		stmt.setInt(selectQuery.getPlaceholderIndex(":avg_age"), 50);
		rs = stmt.executeQuery();


