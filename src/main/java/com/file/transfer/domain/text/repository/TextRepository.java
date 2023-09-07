package com.file.transfer.domain.text.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.file.transfer.domain.text.entity.Text;

public interface TextRepository extends JpaRepository<Text, Long>, TextRespostoryCustom{

}
