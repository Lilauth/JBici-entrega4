package dao;

import java.util.List;

public interface IBaseDAO<T> {
	
	 T buscaPorID(long i);

	 List<T> listar();

	 void persistir(T obj);
	 
	 void update(T obj);

	 void borrar(T obj);

	 void borrarTodos();
	 
	 long contarElementos();

}
