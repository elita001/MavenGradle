package service.interfaces;

import java.util.List;

public interface GenericDAO<Type> {
	List<Type> getAll();

	Type create(Type object);

	Type update(Type object);

	Type getOne(int id);

	void delete(int id);
}
