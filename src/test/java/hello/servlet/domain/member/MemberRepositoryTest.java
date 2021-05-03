package hello.servlet.domain.member;

import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // note. given
        Member member = new Member("hello", 20);

        // note. when
        Member savedMember = memberRepository.save(member);

        // note. then
        Member findMember = memberRepository.findById(savedMember.getId());

        // note. inspect
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        // note. given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // note. when
        List<Member> members = memberRepository.findAll();

        // note. then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members).contains(member1, member2);
    }
}