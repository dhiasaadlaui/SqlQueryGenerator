package tn.esprit.querybuilder.implementations;

import tn.esprit.querybuilder.interfaces.Field;



/**
 * Immutable class to implement all fields.
 * @author Dhia
 */
class AllFields implements Field
{

    /**
     *
     */
    AllFields() { }
	
	@Override
	public String toString()
	{
		return "*";
	}
}
