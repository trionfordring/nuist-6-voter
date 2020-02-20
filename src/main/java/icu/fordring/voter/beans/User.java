package icu.fordring.voter.beans;

import icu.fordring.voter.constant.State;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToMany
    private Set<ImageBean> images = new HashSet<>();

    @OneToMany(targetEntity = ImageBean.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private Set<ImageBean> owns = new HashSet<>();

    public User(){
        state = State.UNAUTHORISED;
    }


}
