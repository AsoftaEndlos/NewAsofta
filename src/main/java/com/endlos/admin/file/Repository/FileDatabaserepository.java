package com.endlos.admin.file.Repository;

import com.endlos.admin.file.model.Fileinfomodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileDatabaserepository extends JpaRepository<Fileinfomodel, Long> {
    @Query("SELECT u FROM Fileuploading_Table  u WHERE u.fileName = ?1")
    Optional<Fileinfomodel> findByFilename(@Param("filename") String filename);

    @Query("SELECT u FROM Fileuploading_Table  u WHERE u.image = ?1")
    void uploadToDatabase(MultipartFile file);

    @Query(value = "select * from Fileuploading_Table ORDER BY id DESC LIMIT 1", nativeQuery = true)
    List<Fileinfomodel> findByIdDesc();
}
