package com.kosmo.funfunhaejwo.jpa.domain.semi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File_info {

    @Column(name = "file_src")
    private String file_src;
    @Column(name = "file_name")
    private String file_name;


}
