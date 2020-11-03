package com.ssafy.IMS.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "essay")
public class Essay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @Column(name = "content")
    private String content;

    @Column(name = "result1")
    private String result1;
    @Column(name = "result2")
    private String result2;
    @Column(name = "result3")
    private String result3;
    @Column(name = "result4")
    private String result4;
    @Column(name = "result5")
    private String result5;
    @Column(name = "result6")
    private String result6;
    @Column(name = "result7")
    private String result7;
    @Column(name = "result8")
    private String result8;

}
