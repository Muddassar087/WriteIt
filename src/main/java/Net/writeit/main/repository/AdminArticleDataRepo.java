package Net.writeit.main.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Net.writeit.main.model.AdminData;

public interface AdminArticleDataRepo extends JpaRepository<AdminData, Integer> {
	
	@Query(value="select auther, count(title) as Total from admin_data group by auther order by Total desc LIMIT 3" ,nativeQuery=true)
	ArrayList<Object[]> getAuthurAndArticle();
	
	@Query(value="select title from admin_data where title LIKE %:term%", nativeQuery=true)
	List<String> findByMatingtitle(String term);
	
	AdminData findByTitle(String title);
	
}
