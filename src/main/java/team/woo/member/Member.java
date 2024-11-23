package team.woo.member;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.ToString;
import team.woo.domain.Schedule;
import team.woo.algorithm.Weight;
import team.woo.algorithm.WeightHistory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String loginId;

    @NotEmpty
    private String name;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @ToString.Exclude
    private List<Schedule> schedules = new ArrayList<>();

    // Weight와 1:1 관계 설정
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "weight_id")
    private Weight weight;

    // WeightHistory와 1:N 관계 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeightHistory> weightHistories = new ArrayList<>();

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        schedule.setMember(this);
    }

    public void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
        schedule.setMember(null);
    }

    public void addWeightHistory(WeightHistory weightHistory) {
        weightHistories.add(weightHistory);
        weightHistory.setMember(this);
    }

    public void removeWeightHistory(WeightHistory weightHistory) {
        weightHistories.remove(weightHistory);
        weightHistory.setMember(null);
    }
}
