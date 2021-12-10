package org.fasttrackit.onlinelibrary.domain;

import javax.persistence.*;

@Entity
public class BorrowCart {

    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

//    private long borrowNrWeeks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "BorrowCart{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}