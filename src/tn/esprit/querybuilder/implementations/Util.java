package tn.esprit.querybuilder.implementations;

import java.util.List;
import tn.esprit.querybuilder.interfaces.Field;




/**
 * Static functions that are used by other implementation classes.
 * @author Dhia
 */
class Util
{

    /**
     *
     * @param table
     * @return
     */
    static String protectTableName(String table)
	{
		return '`' + table + '`';
	}
	
    /**
     *
     * @param builder
     * @param fields
     */
    static void joinFieldNames(StringBuilder builder, Field[] fields)
	{
		for (int i = 0; i < fields.length; i++)
		{
			builder.append(fields[i].toString());
			
			if (i != fields.length - 1)
				builder.append(", ");
		}
	}
	
    /**
     *
     * @param builder
     * @param fields
     */
    static void joinFieldNames(StringBuilder builder, List<Field> fields)
	{
		for (int i = 0; i < fields.size(); i++)
		{
			builder.append(fields.get(i).toString());
			
			if (i != fields.size() - 1)
				builder.append(", ");
		}
	}
	
    /**
     *
     * @param builder
     * @param cnt
     */
    static void createPlaceholders(StringBuilder builder, int cnt)
	{
		for (int i = 0; i < cnt; i++)
		{
			builder.append('?');
			
			if (i != cnt - 1)
				builder.append(", ");
		}
	}
}