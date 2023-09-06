package com.file.transfer.domain.file.entity;

import com.file.transfer.global.entity.BaseEntity;
import com.file.transfer.global.util.AESUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "file")
public class FileEntity extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long id;
    
    private String originalName;
    
    private String fileName;
    
    private String filePath;
    
    private String password;
    
    @Builder
    public FileEntity(Long id, String originalName, String fileName, String filePath) {
        this.id = id;
        this.originalName = originalName;
        this.fileName = fileName;
        this.filePath = filePath;
    }
    
    public void setPassword(String password) {
    	this.password = AESUtil.encrypt(password);
    }

}