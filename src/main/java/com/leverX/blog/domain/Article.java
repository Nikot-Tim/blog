package com.leverX.blog.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String text;

    private Date createdAt;

    private Date updatedAt;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "article_status", joinColumns = @JoinColumn(name = "article_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> statuses;

    public Article(){
    }

    public Article(String title, String text){
        this.title = title;
        this.text = text;
    }
}
