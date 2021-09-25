package com.example.demo.filerepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.fileuploading.Fileinfomodel;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface FileDatabaserepository extends JpaRepository<Fileinfomodel, Long>{
	@Query("SELECT u FROM Fileuploading_Table  u WHERE u.fileName = ?1")
	Optional<Fileinfomodel> findByFilename(@Param ("filename") String filename);
	@Query("SELECT u FROM Fileuploading_Table  u WHERE u.image = ?1")
	public void uploadToDatabase( MultipartFile file);
}
