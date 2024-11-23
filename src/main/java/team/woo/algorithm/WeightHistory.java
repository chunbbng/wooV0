package team.woo.algorithm;

import jakarta.persistence.*;
import lombok.Data;
import team.woo.member.Member;

@Entity
@Data
public class WeightHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double difficultyWeight;
    private double stressWeight;
    private double urgencyWeight;
    private double importanceWeight;

    // Weight 변경 횟수를 기록하는 필드
    private int updateCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_type_id")
    private TaskType taskType;

    // Member와 연관 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public WeightHistory() {
        // 기본 생성자
    }

    public WeightHistory(Weight currentWeight, TaskType taskType, Member member, int updateCount) {
        this.difficultyWeight = currentWeight.getDifficultyWeight();
        this.stressWeight = currentWeight.getStressWeight();
        this.urgencyWeight = currentWeight.getUrgencyWeight();
        this.importanceWeight = currentWeight.getImportanceWeight();
        this.taskType = taskType;
        this.member = member;
        this.updateCount = updateCount;  // 몇 번째 업데이트인지 기록
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
