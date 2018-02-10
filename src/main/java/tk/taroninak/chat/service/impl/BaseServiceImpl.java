package tk.taroninak.chat.service.impl;

import tk.taroninak.chat.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

class BaseServiceImpl<Entity> implements BaseService<Entity> {
    @Autowired
    private PagingAndSortingRepository<Entity, Long> repository;

    @Override
    public List<Entity> findAll(int page, int size) {
        return repository.findAll(new PageRequest(page, size))
                .getContent();
    }

    @Override
    public Entity findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Entity create(Entity entity) {
       return repository.save(entity);
    }

    @Override
    public Entity update(Entity entity) {
        return repository.save(entity);
    }

    @Override
    public Entity delete(Long id) {
        Entity entity = repository.findOne(id);
        repository.delete(id);
        return entity;
    }

    // Setter for injecting mock from tests
    public void setRepository(PagingAndSortingRepository<Entity, Long>  repository) {
        this.repository = repository;
    }
}
