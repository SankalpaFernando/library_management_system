package com.example.SubscriptionManagementSystem.Service;

import com.example.SubscriptionManagementSystem.DTO.Member.CreateMemberDTO;
import com.example.SubscriptionManagementSystem.DTO.Member.GetMemberDTO;
import com.example.SubscriptionManagementSystem.DTO.Member.UpdateMemberDTO;
import com.example.SubscriptionManagementSystem.DTO.PageResponse;
import com.example.SubscriptionManagementSystem.Entity.Member;
import com.example.SubscriptionManagementSystem.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void createMember(CreateMemberDTO memberDTO){
        Member member = new Member(memberDTO.getFirstName(),memberDTO.getLastName(),memberDTO.getJoinedDate(),memberDTO.getAddress(),memberDTO.getPhoneNumber());
        memberRepository.save(member);
    }

    public PageResponse<GetMemberDTO> getMembers(String search,int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return new PageResponse<GetMemberDTO>(memberRepository.searchMember(search,pageable).map(member->new GetMemberDTO(member.getId(),member.getFirstName(),member.getLastName(),member.getJoinedDate(),member.getAddress(),member.getPhoneNumber())));
    }

    public void updateMember(UpdateMemberDTO updateMemberDTO){
        Member member = memberRepository.findById(updateMemberDTO.getId()).orElseThrow();
        member.setFirstName(updateMemberDTO.getFirstName());
        member.setLastName(updateMemberDTO.getLastName());
        member.setJoinedDate(updateMemberDTO.getJoinedDate());
        member.setAddress(updateMemberDTO.getAddress());
        member.setPhoneNumber(updateMemberDTO.getPhoneNumber());
        memberRepository.save(member);
    }

    public void deleteMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(member);

    }
}
