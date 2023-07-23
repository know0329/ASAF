// 이 코드는 현재 파일이 com.codingrecipe.member.entity 패키지에 속해 있다는 것을 나타냅니다.
// 이 패키지는 주로 엔티티 클래스를 포함하며, 데이터베이스 테이블과 객체 간에 매핑을 담당합니다.
package com.codingrecipe.member.entity;
// MemberDTO 클래스를 현재 파일에서 사용하기 위해 com.codingrecipe.member.dto 패키지에서 가져옵니다.
// 이 클래스는 데이터 전송 객체(Data Transfer Object)의 역할을 합니다.
import com.codingrecipe.member.dto.MemberDTO;
// lombok.Getter와 lombok.Setter가 가져옵니다. Lombok 라이브러리는 Java의 보일러플레이트 코드를 줄이기 위하여 사용되며,
// @Getter와 @Setter 어노테이션으로 getter와 setter 메서드를 자동 생성할 수 있습니다.
import lombok.Getter;
import lombok.Setter;
// javax.persistence 패키지의 모든 클래스를 가져옵니다.
// 이 패키지는 단순 및 포괄적인 객체 관리를 위한 JPA(Java Persistence API) 구현을 제공하며, Java 객체와 관계형 데이터베이스의 테이블 간의 매핑이 가능하도록 돕습니다.
import javax.persistence.*;

// @Entity : 클래스가 엔티티임을 나타냅니다. 엔티티 클래스는 데이터베이스의 한 테이블에 해당하는 객체를 나타내며, JPA를 통해 자동으로 테이블과 매핑됩니다.
@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    //  MemberDTO 객체를 데이터베이스에 저장하거나 업데이트하기 위해 MemberEntity로 변환해줍니다.
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }

}
