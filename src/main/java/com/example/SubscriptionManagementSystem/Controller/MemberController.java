package com.example.SubscriptionManagementSystem.Controller;

import com.example.SubscriptionManagementSystem.DTO.Member.CreateMemberDTO;
import com.example.SubscriptionManagementSystem.DTO.Member.GetMemberDTO;
import com.example.SubscriptionManagementSystem.DTO.Member.UpdateMemberDTO;
import com.example.SubscriptionManagementSystem.DTO.PageResponse;
import com.example.SubscriptionManagementSystem.Service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(@Valid @RequestBody CreateMemberDTO createMemberDTO){
        memberService.createMember(createMemberDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public PageResponse<GetMemberDTO> getMembers(
            @RequestParam String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return memberService.getMembers(search,page,size);
    }

    @PutMapping
    public ResponseEntity<Void> updateMember(@Valid @RequestBody UpdateMemberDTO updateMemberDTO){
        memberService.updateMember(updateMemberDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@RequestParam("member_id") Long member_id){
        memberService.deleteMember(member_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
