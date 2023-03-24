package customer.springcustomer.service;

import customer.springcustomer.domain.Member;
import customer.springcustomer.repository.MemberRepository;
import customer.springcustomer.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        // 일단 저장이 되는지 검증
        // gien
        Member member1= new Member();
        member1.setName("hello");

        // when
        Long saveId= memberService.join(member1);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member1.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원예외(){
        // given
        Member member1= new Member();
        member1.setName("spring");

        Member member2= new Member(); // 이름 바꾸기: shift+f6
        member2.setName("spring");

        //when
        memberService.join(member1);
    //  assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }
}