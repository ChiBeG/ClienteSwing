package services.domain.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Repository<E extends Entity, D extends EntityDTO> implements IRepository<E> {

    protected abstract IDAO<D> getDAO();

    protected abstract D toDTO(E entity);

    protected abstract E toEntity(D dto);

    

    @Override
    public void add(E entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
            getDAO().insert(toDTO(entity));
        }
        else {
            getDAO().update(toDTO(entity));
        }
    }

    @Override
    public void remove(E entity) {
        if (entity.getId() != null) {
            getDAO().delete(toDTO(entity));
            entity.setId(null);
        }
    }

    @Override
    public List<E> findAll() {
        List<E> entidades = new ArrayList<>();
        for (var dto : getDAO().findAll()) {
            entidades.add(toEntity(dto));
        }
        return entidades;
    }
}
