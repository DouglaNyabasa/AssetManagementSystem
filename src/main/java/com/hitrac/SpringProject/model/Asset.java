package com.hitrac.SpringProject.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "time_In" ,nullable = false)
    @CreationTimestamp
    private LocalDateTime time_In;

    @Column(name = "time_Out")
    @UpdateTimestamp
    private LocalDateTime time_Out;

    @Column(name = "description")
    private String description;


    @Column(name = "branch" ,nullable = false)
    @Enumerated(EnumType.STRING)
    private Branch branch;
}
