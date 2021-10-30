package Net.writeit.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Net.writeit.main.model.Profile;
import Net.writeit.main.repository.UserProfileRepository;

@Service
public class ProfileUpadetService {
	@Autowired
	UserProfileRepository upr;
	
	public void deleteUser(Profile p) {
		upr.deleteById(p.getId());
	}
	public void SaveUpdatedUser(Profile NewUser) {
		upr.updateByEmail(NewUser.getEmail(), NewUser.getName(), NewUser.getAge(), NewUser.getCountry(), 
				NewUser.getDateOfJoin(), NewUser.getDescription(), NewUser.getPic());
	}
}
