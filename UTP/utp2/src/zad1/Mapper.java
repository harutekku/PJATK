/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;


public interface Mapper<V,T>{ // Uwaga: interfejs musi być sparametrtyzowany
	T map(V v);
}  
