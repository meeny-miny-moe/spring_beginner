package customer.springcustomer.repository;

import customer.springcustomer.domain.Member;
import customer.springcustomer.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository=new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore(); // 테스트가 끝날때마다 실행
    }
    @Test
    public void save(){
        Member member=new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        //System.out.println("result = "+(result==member));
        //Assertions.assertEquals(member, null); // 기대값, 실제값
        assertThat(member).isEqualTo(result); // 멤버가 리절트랑 같다
    }
    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member(); // shift+f6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
