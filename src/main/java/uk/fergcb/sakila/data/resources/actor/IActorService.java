package uk.fergcb.sakila.data.resources.actor;

import org.springframework.data.domain.Pageable;

public interface IActorService {
    ActorCollection readActors(Pageable pageable);
    ActorCollection readActorsByName(String name, Pageable pageable);
    Actor readActor(Integer id);
    Integer createActor(ActorDTO actorDTO);
    void updateActor(Integer id, ActorDTO actorDTO);
    void deleteActor(Integer id);
}
