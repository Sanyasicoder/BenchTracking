package com.centric.bench.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="developer")
@Setter
@Getter
@Component
@ToString
public class Developer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    private String lastProjectName;
    private String name;
    private boolean onBench;
    

   
}
