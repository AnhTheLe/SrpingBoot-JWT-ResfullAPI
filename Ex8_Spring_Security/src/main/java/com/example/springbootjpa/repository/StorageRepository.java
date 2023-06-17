package com.example.springbootjpa.repository;

import com.example.springbootjpa.entity.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, Long> {

    Optional<StorageEntity> findById(Long id);



    /**
     * Khi thực hiện câu lênnh insert, update, delete thì cần thêm @Modifying và @Transactional
     * @param id
     * @param name
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE Storage s SET s.name = :name WHERE s.id = :id", nativeQuery = true)
    int updateStorageNameById(@Param("id") Long id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Storage s SET s.address = :address WHERE s.id = :id", nativeQuery = true)
    int updateStorageAddressById(@Param("id") Long id, @Param("address") String address);

    /**
    Annotation @Modifying được sử dụng để đánh dấu các câu lệnh truy vấn UPDATE, DELETE hoặc INSERT trong Spring Data JPA.
    Khi sử dụng annotation này, Spring Data JPA sẽ biết được rằng câu lệnh truy vấn đó là một câu lệnh thay đổi dữ liệu và sẽ
    thực thi nó trong một transaction riêng biệt. Nếu không sử dụng annotation @Modifying, Spring Data JPA sẽ không thực thi
    câu lệnh truy vấn đó và sẽ báo lỗi
     */

    /**
     * Khi sử dụng hàm save() trong Spring Data JPA, dữ liệu sẽ được lưu vào cơ sở dữ liệu ngay lập tức.
     * Tuy nhiên, để đảm bảo tính toàn vẹn của transaction, nên sử dụng annotation @Transactional để bảo đảm rằng các thay đổi
     * sẽ được lưu lại trong cơ sở dữ liệu. Nếu không sử dụng annotation này, các thay đổi sẽ không được lưu lại trong cơ sở dữ liệu và
     * sẽ bị rollback sau khi phương thức của bạn thực thi xong. Ngoài ra, bạn cũng nên đảm bảo rằng các thay đổi
     * được thực hiện trong quá trình thực thi câu lệnh truy vấn đều hợp lệ và không gây ra lỗi.
     * Nếu có lỗi xảy ra, bạn nên xử lý lỗi đó và rollback transaction để đảm bảo tính toàn vẹn của dữ liệu trong cơ sở dữ liệu.
     */
    /*
    https://codegym.vn/blog/2020/06/11/truy-van-du-lieu-thong-qua-spring-data-jpa/
     */

//    public StorageEntity updateCode(String code);
}
