package jpabook.jpashop.service;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.transaction.annotation.*;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository MemberRepository;
	@Autowired EntityManager em;
	
	@Test
	public void test() throws Exception {
		//given
		Member member = new Member();
		member.setName("kim");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		em.flush(); //insert쿼리를 보고싶다면..
		assertEquals(member, MemberRepository.findOne(saveId));
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");

		//when
		memberService.join(member1);
		memberService.join(member2); //예외가 발생해야한다.
		
		//then
		fail("예외가 발생해야 한다.");
		
	}
}
