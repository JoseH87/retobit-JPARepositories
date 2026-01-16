package dev.marshall_bits.repositories.repositories;

import dev.marshall_bits.repositories.models.Post;
import dev.marshall_bits.repositories.models.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {
    public Post findByTitle(String title);
    List<Post> findByCategory(PostCategory category);

    //Método para obtener una lista de posts que tengan más de 100 vistas. Llámalo findPostsWithMoreThan100Views().
    @Query("""
        SELECT p
        FROM Post p
        WHERE p.viewCount > 100
    """)
    List<Post> findPostsWithMoreThan100Views();

    //Método para obtener una lista de publicaciones ordenadas por fecha de creación, desde la más reciente a la más antigua. Llámalo findAllByCreatedAt().
    @Query("""
        SELECT p
        FROM Post p
        ORDER BY p.createdAt DESC
    """)

    List<Post> findAllByCreatedAt();

    //Método para obtener una lista de publicaciones que contengan una palabra clave en su título. Llámalo findByTitleContaining(String keyword).
    @Query("""
        SELECT p
        FROM Post p
        WHERE p.title LIKE %:keyword%
    """)

    List<Post> findByTitleContaining(@Param("keyword") String keyword);

}
