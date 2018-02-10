package tk.taroninak.chat.service;


import java.util.List;

public interface BaseService<Entity> {

    List<Entity> findAll(int page, int size);

    Entity findById(Long id);

    Entity create(Entity entity);

    Entity update(Entity entity);

    Entity delete(Long id);

}
