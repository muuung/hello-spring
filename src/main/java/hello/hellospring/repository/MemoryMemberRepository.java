package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// alter+enter로 implements methods 만들기
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // store에 member 저장
    // id는 시스템에서 자동지정 해주는 방식 (id == sequence)
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // id 찾기
    // id가 없는 경우 null -> Optional로 감싸 반환하여 에러 방지
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 이름 찾기
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // member 리스트 확인
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}