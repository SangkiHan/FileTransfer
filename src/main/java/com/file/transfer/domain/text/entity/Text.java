package com.file.transfer.domain.text.entity;

import com.file.transfer.domain.text.dto.TextSaveDto;
import com.file.transfer.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class Text extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="text_id")
    private Long id;
    
    @Lob
    private String text;
    
    public void setTexts(TextSaveDto text) {
    	this.id = text.getId();
    	this.text = text.getText();
    }
    
}
