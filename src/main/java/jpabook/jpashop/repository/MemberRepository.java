package jpabook.jpashop.repository;

import java.util.*;

import javax.persistence.*;

import org.springframework.stereotype.*;

import jpabook.jpashop.domain.*;
import lombok.*;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	/*
	 * @PersistenceContext
	private EntityManager em;*/	
	// 위 대신 @RequiredArgsConstructor 써서 아래처럼 써도 된다.
	//spring이 @Autowired도 자동으로 injection 되게 해준다.
	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member);
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll(){
		return em.createQuery("select m from Member m ", Member.class)
				.getResultList();
	}
	
	public List<Member> findByName(String name){
		return em.createQuery("select m from Member m where m.name= :name", Member.class)
				.setParameter("name", name)
				.getResultList();		
	}

}
