package jpabook.jpashop.service;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.*;
import lombok.*;

@Service
//읽기에 readOnly =true 를 넣으면 빠른 성능을 보여줌
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	
	
	private final MemberRepository memberRepository;
	
	/*
	 * === @RequiredArgsConstructor :  아래 코드를 자동으로 완성 ===
	 * final 이 있는 필드에 대해 생성자에 대해서 파라미터로 자동 설정해준다.
	 * @AutoWired
	 * public MemberService(MemberRepository memberRepository) {
	 * this.memberRepository = memberRepository; }
	 */
	

	/**
	 * 회원 가입
	 */
	@Transactional(readOnly = false)
	public Long join(Member member) {
		
		validateDuplicateMember(member);//중복 회원 검증
		
		memberRepository.save(member);
		
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}		
	}
	
	/**
	 * 회원 전체 조회
	 * @return
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
