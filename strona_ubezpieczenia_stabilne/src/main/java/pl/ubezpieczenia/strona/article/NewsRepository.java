package pl.ubezpieczenia.strona.article;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NewsRepository extends PagingAndSortingRepository<NewsDao, Long> {

    List<NewsDao> findAllById(Long id);
    @Query(value = "SELECT n FROM  NewsDao n order by n.timeStamp desc ")
    List<NewsDao> findAllArticles();
    @Query("SELECT distinct s FROM  NewsDao s where s.tag like %:phrase% or s.description like %:phrase%")
    List<NewsDao> findSome(@Param("phrase") String phrase);
}
