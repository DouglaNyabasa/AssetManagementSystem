package com.hitrac.SpringProject.dto;


import com.hitrac.SpringProject.model.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AssetRequestDTO {


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
