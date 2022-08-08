package uk.fergcb.sakila.actor;

public interface IActorService {
    Iterable<Actor> readActors();
    Iterable<Actor> readActorsByName(String name);
    Actor readActor(Integer id);
    void createActor(ActorDTO actorDTO);
    void updateActor(Integer id, ActorDTO actorDTO);
    void deleteActor(Integer id);
}
