package uk.fergcb.sakila.data.resources.actor;

public interface IActorService {
    ActorCollection readActors();
    ActorCollection readActorsByName(String name);
    Actor readActor(Integer id);
    Integer createActor(ActorDTO actorDTO);
    void updateActor(Integer id, ActorDTO actorDTO);
    void deleteActor(Integer id);
}
