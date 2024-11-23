package team.woo.algorithm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import team.woo.member.Member;

@Entity
@Getter
@Setter
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double difficultyWeight;
    private double stressWeight;
    private double urgencyWeight;
    private double importanceWeight;

    @OneToOne(mappedBy = "weight", cascade = CascadeType.ALL)
    private TaskType taskType;

    // Member와 1:1 관계 설정
    @OneToOne(mappedBy = "weight", fetch = FetchType.LAZY)
    private Member member;

    public Weight() {
        // 기본 생성자
    }

    public Weight(double difficultyWeight, double stressWeight, double urgencyWeight, double importanceWeight) {
        this.difficultyWeight = difficultyWeight;
        this.stressWeight = stressWeight;
        this.urgencyWeight = urgencyWeight;
        this.importanceWeight = importanceWeight;
    }

    public static Weight createDefaultWeight(String taskTypeName) {
        switch (taskTypeName) {
            case "회의":
                return new Weight(0.04, 0.05, 0.025, 0.08);
            case "학습":
                return new Weight(0.05, 0.07, 0.03, 0.06);
            case "운동":
                return new Weight(0.06, 0.04, 0.035, 0.07);
            case "보고서 작성":
                return new Weight(0.08, 0.09, 0.05, 0.1);
            case "가사일":
                return new Weight(0.03, 0.02, 0.02, 0.03);
            case "창의적인 작업":
                return new Weight(0.07, 0.1, 0.04, 0.09);
            default:
                throw new IllegalArgumentException("Invalid task type: " + taskTypeName);
        }
    }


    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
