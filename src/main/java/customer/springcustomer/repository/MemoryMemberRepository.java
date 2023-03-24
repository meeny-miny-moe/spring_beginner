package customer.springcustomer.repository;

import customer.springcustomer.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 데이터 저장
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store=new HashMap<>(); // key: id, vlaue: member
    private static long sequence=0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id생성
        store.put(member.getId(),member); // 멤버 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //name과 같은 member를 찾아냄
                .findAny(); // 조건에 일치하는 요소 1개를 리턴

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
