package thalia.atec.thaliaPrototipo.Service;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import thalia.atec.thaliaPrototipo.model.Notify;

public interface NotifyRepository extends MongoRepository<Notify, String> {
	
	List<Notify> findByUseridAndEstado(String userid,int estado);

	List<Notify> findByUseridAndTypeNotAndUserdoNot(String userid,int type,String userdo);
	
}
