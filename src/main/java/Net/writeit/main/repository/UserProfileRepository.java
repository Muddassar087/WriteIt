package Net.writeit.main.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Net.writeit.main.model.Profile;

/**
 * @author MUHAMMAD MUDDASSAR
 *
 */
@Repository
public interface UserProfileRepository extends JpaRepository<Profile, Integer>{
	
	Profile findByEmail(String email);
	
	Profile findByName(String name);
	
	/* this abstract method will update the user using custom query 
	 *  @param (email etc.) is used to fetch data for updation
	 * */
	@Transactional
	@Modifying
	@Query("update Profile set name=?2, age=?3, country=?4, date_of_join=?5, description=?6, pic=?7 where email=?1")
	void updateByEmail(String email, String name, String age, String country, String date, String desc, byte[] picarray);
}
