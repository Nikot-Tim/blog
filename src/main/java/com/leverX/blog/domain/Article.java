package com.leverX.blog.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the title")
    private String title;

    @NotBlank(message = "Please fill the text")
    private String text;

    private Date createdAt;

    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "article_status", joinColumns = @JoinColumn(name = "article_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> statuses;

    @OneToMany(mappedBy = "post",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    public Article(){
    }

    public Article(String title, String text, User user){
        this.title = title;
        this.text = text;
        this.author = user;
    }
    
    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }
}
