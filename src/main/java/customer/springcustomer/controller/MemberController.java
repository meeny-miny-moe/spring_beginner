package customer.springcustomer.controller;

import customer.springcustomer.domain.Member;
import customer.springcustomer.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    // 스프링 컨테이너에 등록 - > 하나만 생서됨
    // alt + insert

    // 컨트롤러랑 서비스랑 연결 -> Autowired -> 디펜더시 인젝션(주입)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members); // 멤버 리스트 자체를 다 model에 담아서 화면에 view에 넘김
        // KEY: members value: 리스트로된 모든 회원
        return "members/memberlist";
    }
}
